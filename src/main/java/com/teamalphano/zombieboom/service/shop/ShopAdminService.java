package com.teamalphano.zombieboom.service.shop;

import com.teamalphano.zombieboom.dto.purchase.UpdateProdPurchaseDto;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdCreateParamsDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdListParamsDto;
import com.teamalphano.zombieboom.dto.shop.admin.ProdUpdateDto;
import com.teamalphano.zombieboom.mapper.item.ItemRelationMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopAdminMapper;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import com.teamalphano.zombieboom.model.shop.ProductLang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ProductDto> getProductListAdmin(ProdListParamsDto shopListDto){
        //TODO: 검색조건 수정하기
        return shopAdminMapper.getProductListAdmin(shopListDto);
    }

    //특정 상품 한개 조회 - admin
    public ProductAdmin getProductDetailAdmin(Integer prodNo){

        ProductAdmin product = shopAdminMapper.getProductDetailAdmin(prodNo);
        List<ProductLang> prodLangs = shopAdminMapper.getProductLangs(prodNo);
        product.setProductLangData(prodLangs);
        List<ProductItem> itemList = shopAdminMapper.getProductItems(prodNo);
        product.setItems(itemList);

        return product;
    }

    //상품 상세정보 등록
    public String createProductAdmin(ProdCreateParamsDto prodCreateParamsDto){
        int create = shopAdminMapper.createProductAdmin(prodCreateParamsDto);
        if(create>0){
            return "success";
        }else {
            return "fail";
        }
    }

    //상품 상세 수정
    public String updateProductDetailAdmin(ProdUpdateDto prodUpdateDto){
        int update = shopAdminMapper.updateProductDetailAdmin(prodUpdateDto);

        List<ProductLang> langs = prodUpdateDto.getLangData();
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

    //구입해서 상품 데이터 업데이트
    public void updateProductAfterPurchase(UpdateProdPurchaseDto updateProdPurchaseDto){
        shopAdminMapper.updateProductAfterPurchase(updateProdPurchaseDto);
    }

}
