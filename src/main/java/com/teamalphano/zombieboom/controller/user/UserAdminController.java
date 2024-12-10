package com.teamalphano.zombieboom.controller.user;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.user.AdminUserListDto;
import com.teamalphano.zombieboom.dto.user.UserLoginDto;
import com.teamalphano.zombieboom.model.user.UserInfo;
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
    public ResponseEntity<ApiResponse<List<UserInfo>>> getAllUsers(
            @RequestBody AdminUserListDto adminUserListDto
    ) {
        try {
            List<UserInfo> userList = userAdminService.getUserListAll(adminUserListDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",userList));
        }catch (Exception e) {
            return ResponseEntity.status(500).body( null);
        }
    }

    //관리자 - 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserInfo>> AdminLogin(
            @RequestBody UserLoginDto userLoginDto) {
        try {
            UserInfo user = userAdminService.AdminLogin(userLoginDto);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", user));
            } else {
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "Not Found ID" , null ));
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
