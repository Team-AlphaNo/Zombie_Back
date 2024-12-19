package com.teamalphano.zombieboom.service.shop;

import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {
    private final ShopMapper shopMapper;

    public ShopService(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }

    // 상품 리스트 조회
    @SneakyThrows
    public List<Product> getAllProducts(Integer prodType, String langType) {
        Product productParam = new Product();
        productParam.setProdType(prodType);
        if(langType.isEmpty()){
            productParam.setLangType("ko");
        }else{
            productParam.setLangType(langType);
        }
        List<Product> products = shopMapper.getProducts(productParam);

        if (products != null) {
            for (Product product : products) {
                // 상세 아이템 리스트 조회
                List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
                product.setItems(items);}
        }else{
            System.out.println("products 리스트가 null입니다.");
        }

        return products;
    }

    //특정 상품 한개 조회 - engine prodNo으로
    @Transactional
    public Product getProductDetailByNo(Integer prodNo, String langType){
        Product productParam = new Product();
        productParam.setProdNo(prodNo);
        productParam.setLangType(langType);

        Product product = shopMapper.getProductDetailByNo(productParam);
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
    public Product getProductDetailById(String prodId, String langType){
        Product productParam = new Product();
        productParam.setProdId(prodId);
        productParam.setLangType(langType);

        Product product = shopMapper.getProductDetailById(productParam);
        if (product != null) {
            List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
            product.setItems(items);
        }else{
            System.out.println("상품 없음");
        }
        return product;
    }


    // 상품 리스트 조회
    public List<Product> getAllProductsAll() {
        List<Product> products = shopMapper.getProductsAll();

        if (products != null) {
            for (Product product : products) {
                // 상세 아이템 리스트 조회
                List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
                product.setItems(items);}
        }else{
            System.out.println("products 리스트가 null입니다.");
        }
        return products;
    }

}
