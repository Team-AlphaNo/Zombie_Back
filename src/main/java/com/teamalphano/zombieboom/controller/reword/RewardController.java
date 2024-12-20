package com.teamalphano.zombieboom.controller.reword;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.reword.RewordParams;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.service.reword.RewordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewordController {
    private final RewordService rewordService;
    public RewordController(RewordService rewordService) {
        this.rewordService = rewordService;
    }

    //리워드
    @PostMapping("/get")
    public ResponseEntity<ApiResponse<UserFullDataDto>> getRewordByType(
            @RequestBody RewordParams rewordParams
    ) {
        try {
            UserFullDataDto userData = rewordService.getRewordByType(rewordParams);
            if(userData == null) {
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",userData));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
