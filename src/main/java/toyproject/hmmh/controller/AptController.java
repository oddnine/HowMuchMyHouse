package toyproject.hmmh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.hmmh.domain.Apt;
import toyproject.hmmh.domain.UserApt;
import toyproject.hmmh.dto.SearchReqDto;
import toyproject.hmmh.service.AptService;

import javax.validation.Valid;
import java.util.List;
import java.util.Scanner;

@RequestMapping("/basic")
@Controller
public class AptController {
    private Scanner sc = new Scanner(System.in);

    @Autowired
    private AptService aptService;

    //사용 시 하단 url을 통해 접속하여 사용해주세요.
    //http://localhost:8080/basic/start?sigungu=서울특별시 성북구&eupmyundong=보문동6가&aptName=보문파크뷰자이&date=202205
    //사용하는 db는 H2 db입니다.
    //테이블 생성은 hmmh/sql/CreateTable.sql문을 실행시켜주시면 됩니다.
    //프로퍼티에 api 키와 db 정보를 입력하시면 됩니다.
    @GetMapping("/start")
    public String mainController(@Valid SearchReqDto searchReqDto, Model model) {

        List<Apt> aptList = aptService.getAptList(searchReqDto.toUserApt(searchReqDto), searchReqDto.getDate());

        if(aptList.isEmpty()){
            return "error/500";
        }

        model.addAttribute("aptList", aptList);
        model.addAttribute("aptInfo", aptList.get(0));

        return "basic/items";
    }
}
