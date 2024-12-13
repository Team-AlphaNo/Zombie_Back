package com.teamalphano.zombieboom.controller.item;

import com.teamalphano.zombieboom.dto.item.ItemListDto;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.model.item.ItemData;
import com.teamalphano.zombieboom.service.item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/admin")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //아이템 목록 - admin
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<List<ItemData>>> getItemListAdmin(
            @RequestBody ItemListDto itemListDto) {
        try {
            List<ItemData> data = itemService.getItemListAdmin(itemListDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //아이템 목록 조회 - admin
    @GetMapping("/prodItems")
    public ResponseEntity<ApiResponse<List<ItemData>>> getProductItemListAdmin(
            @RequestParam(value = "prodNo", required = true) Integer prodNo) {
        try {
            List<ItemData> data = itemService.getProductItemListAdmin(prodNo);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
