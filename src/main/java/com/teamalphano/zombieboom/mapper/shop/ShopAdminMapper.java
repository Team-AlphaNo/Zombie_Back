package com.teamalphano.zombieboom.mapper.shop;

import com.teamalphano.zombieboom.dto.shop.ShopCreateDto;
import com.teamalphano.zombieboom.dto.shop.ShopListDto;
import com.teamalphano.zombieboom.dto.shop.ShopUpdateDto;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.model.shop.ProductLang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopAdminMapper {

    List<Product> getProductListAdmin(ShopListDto shopListDto);

    //관리자 - 상품 상세 조회
    ProductAdmin getProductDetailAdmin(Integer prodNo);

    //관리자 - 상품 언어 데이터 조회
    List<ProductLang> getProductLangs(Integer prodNo);

    //상품 상세 등록
    int createProductAdmin(ShopCreateDto shopCreateDto);

    //상품 상세 수정
    int updateProductDetailAdmin(ShopUpdateDto shopUpdateDto);

    //상품 상세 삭제
    int deleteProductAdmin(Integer prodNo);

    //상품 active 수정
    int updateProductActive(Integer prodNo);

    //상품 언어 수정
    void updateProductLang(ProductLang productLang);

}
