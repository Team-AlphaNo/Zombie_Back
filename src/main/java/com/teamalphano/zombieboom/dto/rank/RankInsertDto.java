package com.teamalphano.zombieboom.dto.rank;

public class RankInsertDto {
    private Integer rankValue;
    private Integer userNo;

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "RankInsertDto{" +
                "rankValue=" + rankValue +
                ", userNo=" + userNo +'}';
    }
}
