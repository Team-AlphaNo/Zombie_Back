package com.teamalphano.zombieboom.controller.item;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.item.ReqItemRelationDto;
import com.teamalphano.zombieboom.service.item.ItemRelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemRel/admin")
public class ItemRelationController {
    private final ItemRelationService itemRelationService;

    public ItemRelationController(ItemRelationService itemRelationService) {
        this.itemRelationService = itemRelationService;
    }

    //아이템 - 상품 연관 추가
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addItemRelationsToProd(
            @RequestBody ReqItemRelationDto reqItemRelationDto
    ){
        try{
            String data = itemRelationService.addItemRelationsToProd(reqItemRelationDto);
            if(data.equals("success")){
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", "Successfully add Relations"));
            }else{
                return ResponseEntity.status(500).body(new ApiResponse<>(500, data, null));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //아이템 - 상품 연관 삭제
    @PostMapping("/remove")
    public ResponseEntity<ApiResponse<String>> removeItemRelationsToProd(
            @RequestBody ReqItemRelationDto reqItemRelationDto
    ){
        try{
            String data = itemRelationService.removeItemRelationsToProd(reqItemRelationDto);
            if(data.equals("success")){
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", "Successfully add Relations"));
            }else{
                return ResponseEntity.status(500).body(new ApiResponse<>(500, data, null));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
