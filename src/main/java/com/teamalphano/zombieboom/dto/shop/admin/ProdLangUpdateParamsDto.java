package com.teamalphano.zombieboom.dto.shop.admin;

import lombok.Data;

@Data
public class ProdLangUpdateParamsDto {
    private Integer prodLangNo;
    private Integer prodNo;
    private String prodLangType;
    private String prodName;
    private String prodDesc;
}
