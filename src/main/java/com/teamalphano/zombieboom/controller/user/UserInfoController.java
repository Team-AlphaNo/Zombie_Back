package com.teamalphano.zombieboom.controller.user;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.user.GoogleLoginDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.service.user.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    //Guest 로그인
    @GetMapping("/guest/login")
    public ResponseEntity<ApiResponse<UserFullDataDto>> GuestLogin(
            @RequestParam(value = "uuid", required = true) String uuid ) {
        if (uuid == null || uuid.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(404, "Missing 'uuid' parameter", null));
        }
        try {
            UserFullDataDto data = userInfoService.GuestLogin(uuid);
            if(data == null) {
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "SignIn guest error", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Guest Sign in complete successfully", data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //구글 로그인
    @PostMapping("/google/login")
    public ResponseEntity<ApiResponse<UserFullDataDto>> GoogleLogin(
            @RequestBody GoogleLoginDto googleLoginDto) {
        try {
            UserFullDataDto data = userInfoService.GoogleLogin(googleLoginDto);
            if(data == null) {
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "SignIn guest error", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Guest Sign in complete successfully", data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //탈퇴
    @GetMapping("/deleteAccount")
    public ResponseEntity<ApiResponse<String>> deleteAccount( @RequestParam(value = "uuid", required = true) String uuid ) {
        try {
            String check = userInfoService.deleteAccount(uuid);
            if(check.endsWith("Success")) {
                return ResponseEntity.ok(new ApiResponse<>(200, "Delete Account complete successfully", check));
            }else{
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Delete Account error", check));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    //로그아웃
    @PostMapping("/signOut")
    public ResponseEntity<ApiResponse<String>> signOut( @RequestBody UserFullDataDto userFullDataDto ) {
        try {
            String check = userInfoService.signOut(userFullDataDto);
            if(check.endsWith("Success")) {
                return ResponseEntity.ok(new ApiResponse<>(200, "Sign Out complete successfully", check));
            }else{
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Sign Out error", check));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
