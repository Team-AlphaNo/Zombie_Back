package com.teamalphano.zombieboom.dto.shop.admin;

import lombok.Data;

import java.util.List;

@Data
public class ProdListParamsDto {
    private List<Integer> type;
    private List<Integer> priceType;
    private List<Boolean> activeType;
    private String searchType;
    private String searchValue;
}
