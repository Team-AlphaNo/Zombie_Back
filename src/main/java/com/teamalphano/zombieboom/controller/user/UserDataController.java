package com.teamalphano.zombieboom.controller.user;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.user.UserBuyParamsDto;
import com.teamalphano.zombieboom.model.user.UserData;
import com.teamalphano.zombieboom.service.user.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/data")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {this.userDataService = userDataService;}

    //아이템 구입
    @PostMapping("/buy")
    public ResponseEntity<ApiResponse<UserData>> buyProduct(@RequestBody UserBuyParamsDto userBuyParamsDto) {
        if(userBuyParamsDto.getUserNo() == 0 || userBuyParamsDto.getProdId() == null){
            ResponseEntity.badRequest().body(new ApiResponse<>(400, "Bad Request 400", null));
        }
        try{
            UserData userData = userDataService.buyProduct(userBuyParamsDto);
            if(userData != null){
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", userData));
            }else{
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "404 Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //유저 데이터 상세
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<UserData>> getUserData(@RequestParam(value = "userNo", required = true) Integer userNo) {
        try{
            UserData userData = userDataService.getUserData(userNo);
            if(userData != null){
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", userData));
            }else{
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500 , "Internal server error", null));
        }
    }
}
