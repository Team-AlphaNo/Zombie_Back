package com.teamalphano.zombieboom.dto.shop;

import java.util.List;

public class ShopListDto {
    private List<Integer> type;
    private List<Integer> priceType;
    private List<Boolean> activeType;
    private String searchType;
    private String searchValue;

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }

    public List<Integer> getPriceType() {
        return priceType;
    }

    public void setPriceType(List<Integer> priceType) {
        this.priceType = priceType;
    }

    public List<Boolean> getActiveType() {
        return activeType;
    }

    public void setActiveType(List<Boolean> activeType) {
        this.activeType = activeType;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
