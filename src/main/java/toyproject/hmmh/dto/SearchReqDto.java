package toyproject.hmmh.dto;

import lombok.Data;
import toyproject.hmmh.domain.UserApt;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchReqDto {
    @NotEmpty
    private String sigungu;
    @NotEmpty
    private String eupmyundong;
    @NotEmpty
    private String aptName;
    private int date;

    public UserApt toUserApt(SearchReqDto searchReqDto) {
        return new UserApt(searchReqDto.getSigungu(), searchReqDto.getEupmyundong(), searchReqDto.getAptName());
    }
}
