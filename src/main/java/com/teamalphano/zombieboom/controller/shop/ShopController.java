package com.teamalphano.zombieboom.controller.shop;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
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

    //상품 상세 조회 - client
    @GetMapping("/product/detail")
    public ResponseEntity<ApiResponse<ProductDto>> getProductDetail(
            @RequestParam(value = "prodNo", required = true) Integer prodNo,
            @RequestParam(value = "langType", required = false, defaultValue = "ko") String langType) {
        try {
            ProductDto prod = shopService.getProductDetailByNo(prodNo, langType);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", prod));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 목록 - 전체
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductsAll(
            @RequestParam(value = "userNo", required = true) Integer userNo,
            @RequestParam(value = "langType", required = false, defaultValue = "ko") String langType
    ) {
        try {
            List<ProductDto> prodList = shopService.getAllProductsAll(userNo, langType);
            if(prodList.isEmpty()){
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", Collections.emptyList()));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "AllProducts", prodList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
