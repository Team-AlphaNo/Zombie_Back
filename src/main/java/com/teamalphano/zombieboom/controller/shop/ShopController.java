package com.teamalphano.zombieboom.controller.shop;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.service.shop.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    //상품 목록 - client
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts(
            @RequestParam(value = "prodType", required = false, defaultValue = "0") Integer prodType,
            @RequestParam(value = "langType", required = false, defaultValue = "ko") String langType
    ) {
        try {
            List<Product> prodList = shopService.getAllProducts(prodType,langType);
            if (prodList == null) {
                prodList = Collections.emptyList();
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", prodList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 상세 조회 - client
    @GetMapping("/product/detail")
    public ResponseEntity<ApiResponse<Product>> getProductDetail(
            @RequestParam(value = "prodNo", required = true) Integer prodNo,
            @RequestParam(value = "langType", required = false, defaultValue = "ko") String langType) {
        try {
            Product prod = shopService.getProductDetailByNo(prodNo, langType);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", prod));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 목록 - 전체
    @GetMapping("/products/all")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsAll() {
        try {
            List<Product> prodList = shopService.getAllProductsAll();
            return ResponseEntity.ok(new ApiResponse<>(200, "AllProducts", prodList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
