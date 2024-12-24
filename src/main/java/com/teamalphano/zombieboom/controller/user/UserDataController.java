package com.teamalphano.zombieboom.controller.user;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.user.UpdateTicketDto;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.service.user.UserDataService;
import com.teamalphano.zombieboom.service.user.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/data")
public class UserDataController {
    private final UserDataService userDataService;
    private final UserInfoService userInfoService;

    public UserDataController(
            UserDataService userDataService,
            UserInfoService userInfoService) {
        this.userDataService = userDataService;
        this.userInfoService = userInfoService;
    }

    //유저 데이터 상세
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<UserDataDto>> getUserData(@RequestParam(value = "userNo", required = true) Integer userNo) {
        try{
            UserDataDto userDataDto = userDataService.getUserData(userNo);
            if(userDataDto != null){
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", userDataDto));
            }else{
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500 , "Internal server error", null));
        }
    }

    @PostMapping("/ticketUpdate")
    public ResponseEntity<ApiResponse<UserFullDataDto>> updateUserTicket(
            @RequestBody UpdateTicketDto updateTicketDto
    ) {
        try{
            int update = userDataService.updateUserTicket(updateTicketDto);
            if(update > 0){
                UserFullDataDto fullData = userInfoService.getUserFullData(updateTicketDto.getUserNo());
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", fullData));
            }else{
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "Not Found", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse<>(500 , "Internal server error", null));
        }
    }
}
