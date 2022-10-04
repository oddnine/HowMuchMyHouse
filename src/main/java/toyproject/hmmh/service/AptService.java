package toyproject.hmmh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toyproject.hmmh.api.GetApi;
import toyproject.hmmh.domain.Apt;
import toyproject.hmmh.domain.RegionCodeItem;
import toyproject.hmmh.domain.UserApt;
import toyproject.hmmh.dto.SearchReqDto;
import toyproject.hmmh.getdata.GetTxtData;
import toyproject.hmmh.item.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AptService {
    private final GetTxtData getTxtData;
    private final AptRepository aptRepository;
    private final RegionCodeItemRepository regionCodeItemRepository;
    private final GetApi getApi;

    @PostConstruct
    public void init() {
        getTxtData.saveRegionCodes();
    }

    public List<Apt> getAptList(UserApt userApt, int date) {
        int year = date / 100;
        int day = date - year * 100;
        List<Apt> aptList = aptRepository.findByInfo(userApt, year, day);

        List<Apt> tradedMyApt = new ArrayList<>();

        if (aptList.isEmpty()) {
            RegionCodeItem region = regionCodeItemRepository.findBySigunguCode(userApt.getSigungu() + " " + userApt.getDong());
            if (region == null) {
                System.out.println("지역 입력을 바르게 하십시오.");
                return aptList;
            }
            List<Apt> apiDatas = getApi.getApiData(region, String.valueOf(date));

            if (!apiDatas.isEmpty()) {
                for (Apt apiData : apiDatas) {
                    if (apiData.getAptName().replace("아파트", "").contains(userApt.getAptName().replace("아파트", ""))) {
                        aptRepository.save(apiData);
                        tradedMyApt.add(apiData);
                    }
                }
                if (tradedMyApt.isEmpty()) {
                    System.out.println("조회된 내용이 없습니다.");
                    return tradedMyApt;
                }
            } else {
                System.out.println("조회된 내용이 없습니다.");
                return apiDatas;
            }
            printAptTradedInfo(tradedMyApt);
            return tradedMyApt;
        } else {
            printAptTradedInfo(aptList);
            return aptList;
        }
    }

    private void printAptTradedInfo(List<Apt> aptList) {
        System.out.println("┌──────────────────────────────────────────────────┐");
        System.out.println("│ 아파트 이름: " + aptList.get(0).getAptName());
        System.out.println("│ 아파트 주소: " + aptList.get(0).getSigungu() + " " + aptList.get(0).getDong());
        System.out.println("│ 아파트 건축년도: " + aptList.get(0).getBuildYear() + "년                             │");
        System.out.println("├──────────────────────────────────────────────────┤");
        System.out.println("│ 총 " + aptList.size() + "건의 거래                                      │");
        System.out.println("├──────────────────────────────────────────────────┤");

        for (Apt apt : aptList) {
            System.out.println("│ 거래 날짜: " + apt.getDealYear() + "년 " + apt.getDealMonth() + "월 " + apt.getDealDay() + "일");
            System.out.println("│ 전용 면적 = " + apt.getDedicatedArea() + "m2");
            System.out.println("│ 층: " + apt.getFloor());
            System.out.println("│ 거래금액: " + apt.getAmount() + " (단위: 십만 원)");
            System.out.println("├──────────────────────────────────────────────────┤");
        }
        System.out.println("└──────────────────────────────────────────────────┘");
    }
}
