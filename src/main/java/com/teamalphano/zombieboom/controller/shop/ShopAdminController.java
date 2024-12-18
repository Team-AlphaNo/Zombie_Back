package com.teamalphano.zombieboom.controller.shop;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.shop.ShopCreateDto;
import com.teamalphano.zombieboom.dto.shop.ShopListDto;
import com.teamalphano.zombieboom.dto.shop.ShopUpdateDto;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductAdmin;
import com.teamalphano.zombieboom.service.shop.ShopAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/admin")
public class ShopAdminController {
    private final ShopAdminService shopAdminService;

    public ShopAdminController(ShopAdminService shopAdminService) {
        this.shopAdminService = shopAdminService;
    }

    private static final String UPLOAD_DIR = "/home/ubuntu/project/front/www/uploads/";

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
            @RequestPart(value = "prodImage", required = true) MultipartFile prodImage,
            @RequestPart("productData") ShopCreateDto shopCreateDto) {
        try {
            String fileName = UUID.randomUUID() + "_" + prodImage.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            prodImage.transferTo(filePath.toFile());

            String fileUrl = "teamalphano.site:80/uploads/" + fileName;
            shopCreateDto.setProdImgKey(fileUrl);

            String message = shopAdminService.createProductAdmin(shopCreateDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", message));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상품 수정 - admin
    @PostMapping("/product/update")
    public ResponseEntity<ApiResponse<String>> updateProductDetailAdmin(
            @RequestPart(value = "prodImage", required = false) MultipartFile prodImage,
            @RequestPart("productData") ShopUpdateDto shopUpdateDto
    ) {
        try {
            String fileName = UUID.randomUUID() + "_" + prodImage.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            prodImage.transferTo(filePath.toFile());

            String fileUrl = "teamalphano.site:80/uploads/" + fileName;
            shopUpdateDto.setProdImgKey(fileUrl);

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


}
