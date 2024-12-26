package com.teamalphano.zombieboom.controller.user;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.user.admin.LoginParamsDto;
import com.teamalphano.zombieboom.dto.user.admin.UserFullDataAdminDto;
import com.teamalphano.zombieboom.dto.user.admin.UserInfoAdminDto;
import com.teamalphano.zombieboom.dto.user.admin.UserListParamsDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.dto.user.UserInfoDto;
import com.teamalphano.zombieboom.service.user.UserAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/admin")
public class UserAdminController {
    private final UserAdminService userAdminService;

    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    //유저 목록
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<List<UserInfoAdminDto>>> getAllUsers(
            @RequestBody UserListParamsDto userListParamsDto
    ) {
        try {
            List<UserInfoAdminDto> userList = userAdminService.getUserListAll(userListParamsDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",userList));
        }catch (Exception e) {
            return ResponseEntity.status(500).body( null);
        }
    }

    //관리자 - 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserInfoAdminDto>> AdminLogin(
            @RequestBody LoginParamsDto loginParamsDto) {
        try {
            UserInfoAdminDto user = userAdminService.AdminLogin(loginParamsDto);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", user));
            } else {
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "Not Found ID" , null ));
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //상세정보
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<UserFullDataAdminDto>> getUserDataDetail(
            @RequestParam(value = "userNo", required = true) int userNo
    ) {
        try {
            UserFullDataAdminDto data = userAdminService.getUserDataDetail(userNo);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",data));
        }catch (Exception e) {
            return ResponseEntity.status(500).body( null);
        }
    }
}
