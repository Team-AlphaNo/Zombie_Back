package com.teamalphano.zombieboom.mapper.shop;

import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    // 상품 리스트 조회
    List<Product> getProducts(Product product);

    // 특정 상품의 아이템 리스트 조회
    List<ProductItem> getProductItem(Integer prodNo);
    
    //특정 상품 상세 조회
    Product getProductDetail(Product product);

    //구매 후 상점 데이터 업데이트
    int updateProductAfterPurchase(UserBuyDto userBuyDto);


    // 상품 리스트 조회
    List<Product> getProductsAll();
}
