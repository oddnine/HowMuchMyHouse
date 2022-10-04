package toyproject.hmmh.dto;

import lombok.Data;
import toyproject.hmmh.domain.UserApt;

@Data
public class SearchReqDto {
    private String sigungu;
    private String eupmyundong;
    private String aptName;
    private int date;

    public UserApt toUserApt(SearchReqDto searchReqDto) {
        return new UserApt(searchReqDto.getSigungu(), searchReqDto.getEupmyundong(), searchReqDto.getAptName());
    }
}
