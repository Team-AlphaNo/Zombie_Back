package com.teamalphano.zombieboom.dto.rank;

import lombok.Data;

@Data
public class RankDto {
    private String rankNo;
    private Integer rankValue;
    private String createDate;

    //userInfo
    private Integer userNo;
    private String userId;
    private String userName;
}
