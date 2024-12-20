package com.teamalphano.zombieboom.mapper.shop;

import com.teamalphano.zombieboom.dto.purchase.UpdateProdPurchaseDto;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdCreateParamsDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdListParamsDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdUpdateDto;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.model.shop.ProductLang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopAdminMapper {

    List<ProductDto> getProductListAdmin(ProdListParamsDto shopListDto);

    //관리자 - 상품 상세 조회
    ProductAdmin getProductDetailAdmin(Integer prodNo);

    //관리자 - 상품 언어 데이터 조회
    List<ProductLang> getProductLangs(Integer prodNo);

    //상품 상세 등록
    int createProductAdmin(ProdCreateParamsDto prodCreateParamsDto);

    //상품 상세 수정
    int updateProductDetailAdmin(ProdUpdateDto prodUpdateDto);

    //상품 상세 삭제
    int deleteProductAdmin(Integer prodNo);

    //상품 active 수정
    int updateProductActive(Integer prodNo);

    //상품 언어 수정
    void updateProductLang(ProductLang productLang);

    //구입시 상품 정보 수정
    void updateProductAfterPurchase(UpdateProdPurchaseDto updateProdPurchaseDto);
}
