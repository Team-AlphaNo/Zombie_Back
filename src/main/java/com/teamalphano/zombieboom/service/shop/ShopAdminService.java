package com.teamalphano.zombieboom.service.shop;

import com.teamalphano.zombieboom.dto.shop.ShopCreateDto;
import com.teamalphano.zombieboom.dto.shop.ShopListDto;
import com.teamalphano.zombieboom.dto.shop.ShopUpdateDto;
import com.teamalphano.zombieboom.mapper.item.ItemRelationMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopAdminMapper;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.model.shop.ProductLang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopAdminService {
    private final ShopAdminMapper shopAdminMapper;
    private final ItemRelationMapper itemRelationMapper;

    public ShopAdminService(ShopAdminMapper shopAdminMapper, ItemRelationMapper itemRelationMapper) {
        this.shopAdminMapper = shopAdminMapper;
        this.itemRelationMapper = itemRelationMapper;
    }

    @Transactional
    public List<Product> getProductListAdmin(ShopListDto shopListDto){
        //TODO: 검색조건 수정하기
        return shopAdminMapper.getProductListAdmin(shopListDto);
    }

    //특정 상품 한개 조회 - admin
    public ProductAdmin getProductDetailAdmin(Integer prodNo){

        ProductAdmin product = shopAdminMapper.getProductDetailAdmin(prodNo);
        List<ProductLang> prodLangs = shopAdminMapper.getProductLangs(prodNo);

        product.setProductLangData(prodLangs);

        return product;
    }

    //상품 상세정보 등록
    public String createProductAdmin(ShopCreateDto shopCreateDto){
        int create = shopAdminMapper.createProductAdmin(shopCreateDto);
        if(create>0){
            return "success";
        }else {
            return "fail";
        }
    }

    //상품 상세 수정
    public String updateProductDetailAdmin(ShopUpdateDto shopUpdateDto){
        int update = shopAdminMapper.updateProductDetailAdmin(shopUpdateDto);

        List<ProductLang> langs = shopUpdateDto.getLangData();
        if(!langs.isEmpty()){
            for(int i=0; i<langs.size(); i++){
                shopAdminMapper.updateProductLang(langs.get(i));
            }
        }
        if(update>0){
            return "success";
        }else {
            return "fail";
        }
    }

    //상품 상세 삭제
    public String deleteProductAdmin(Integer prodNo){
        int delete = shopAdminMapper.deleteProductAdmin(prodNo);
        if(delete>0){
            int delItemRelation = itemRelationMapper.deleteItemRelationByProdNo(prodNo);
            if(delItemRelation>0){
                return "success";
            }else{
                return "fail";
            }
        }else {
            return "fail";
        }
    }
    

}
