package com.teamalphano.zombieboom.controller.shop;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.shop.ShopCreateDto;
import com.teamalphano.zombieboom.dto.shop.ShopListDto;
import com.teamalphano.zombieboom.dto.shop.ShopUpdateDto;
import com.teamalphano.zombieboom.model.item.ItemData;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.service.shop.ShopAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/admin")
public class ShopAdminController {
    private final ShopAdminService shopAdminService;

    public ShopAdminController(ShopAdminService shopAdminService) {
        this.shopAdminService = shopAdminService;
    }

    //상품 목록 - admin
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<List<Product>>> getProductListAdmin(
            @RequestBody ShopListDto shopListDto) {
        try {
            System.out.println("ShopListDto: " + shopListDto.toString());
            List<Product> prod = shopAdminService.getProductListAdmin(shopListDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", prod));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 상세 조회 - admin
    @GetMapping("/product/detail")
    public ResponseEntity<ApiResponse<ProductAdmin>> getProductDetailAdmin(
            @RequestParam(value = "prodNo", required = true) Integer prodNo) {
        try {
            ProductAdmin prod = shopAdminService.getProductDetailAdmin(prodNo);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", prod));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 등록 - admin
    @PostMapping("/product/create")
    public ResponseEntity<ApiResponse<String>> createProductDetailAdmin(
            @RequestBody ShopCreateDto shopCreateDto) {
        try {
            String message = shopAdminService.createProductAdmin(shopCreateDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", message));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 수정 - admin
    @PostMapping("/product/update")
    public ResponseEntity<ApiResponse<String>> updateProductDetailAdmin(
            @RequestBody ShopUpdateDto shopUpdateDto
    ) {
        try {
            String response = shopAdminService.updateProductDetailAdmin(shopUpdateDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", response));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 삭제 - admin
    @GetMapping("/product/delete")
    public ResponseEntity<ApiResponse<String>> deleteProductAdmin(
            @RequestParam Integer prodNo
    ) {
        try {
            String response = shopAdminService.deleteProductAdmin(prodNo);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", response));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 상세 아이템 목록 조회 - admin
    @GetMapping("/product/detail/item")
    public ResponseEntity<ApiResponse<List<ItemData>>> getProductDetailItemListAdmin(
            @RequestParam(value = "prodNo", required = true) Integer prodNo) {
        try {
            List<ItemData> data = shopAdminService.getProductDetailItemListAdmin(prodNo);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
