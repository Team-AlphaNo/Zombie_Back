package com.teamalphano.zombieboom.service.shop;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.mapper.user.UserDataMapper;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShopService {
    private final ShopMapper shopMapper;
    private final UserDataMapper userDataMapper;

    public ShopService(ShopMapper shopMapper, UserDataMapper userDataMapper) {
        this.shopMapper = shopMapper;
        this.userDataMapper = userDataMapper;
    }

    // 상품 리스트 조회
    public List<ProductDto> getAllProductsAll(Integer userNo, String langType) {
        //상품 데이터 조회
        List<ProductDto> products = shopMapper.getProductsAll(langType);

        if (products == null || products.isEmpty()) {
            System.out.println("products 리스트가 비어있거나 null입니다.");
            return null;
        }

        for (ProductDto product : products) {
            // 상세 아이템 리스트 조회
            List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
            product.setItems(items);
        }

        //유저 데이터 조회
        UserDataDto userDataDto = userDataMapper.getUserData(userNo);

        if(userDataDto == null) {
            System.out.println("유저 정보가 없습니다.");
            return null;
        }

        //일회성 상품
        String uniqProd = userDataDto.getUniqProdList();
        if(uniqProd != null && !uniqProd.isEmpty()) {
            System.out.println("uniq is exist");

            // 문자열에서 대괄호 제거 및 파싱
            CharStringEdit charStringEdit = new CharStringEdit();
            List<Integer> userProdNo = charStringEdit.getIntList(uniqProd);
            Set<Integer> userProdNoSet = new HashSet<>(userProdNo);

            // 상품 목록과 비교하여 매칭되는 상품 변경
            for (ProductDto product : products) {
                if (userProdNoSet.contains(product.getProdNo())) {
                    product.setPurchased(true);
                }
            }
        }
        return products;
    }


    //특정 상품 한개 조회 - engine prodNo으로
    @Transactional
    public ProductDto getProductDetailByNo(Integer prodNo, String langType){
        ProductDto productParam = new ProductDto();
        productParam.setProdNo(prodNo);
        productParam.setLangType(langType);

        ProductDto product = shopMapper.getProductDetailByNo(productParam);
        if (product != null) {
            List<ProductItem> items = shopMapper.getProductItemByNo(prodNo);
            product.setItems(items);
        }else{
            System.out.println("상품 없음");
        }
        return product;
    }

    //특정 상품 한개 조회 - engine prodId으로
    @Transactional
    public ProductDto getProductDetailById(String prodId, String langType){
        ProductDto productParam = new ProductDto();
        productParam.setProdId(prodId);
        productParam.setLangType(langType);

        ProductDto product = shopMapper.getProductDetailById(productParam);
        if (product != null) {
            List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
            product.setItems(items);
        }else{
            System.out.println("상품 없음");
        }
        return product;
    }
}
