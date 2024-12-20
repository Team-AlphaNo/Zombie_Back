package com.teamalphano.zombieboom.controller.reword;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.reward.RewardParams;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.service.reward.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {
    private final RewardService rewardService;
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    //리워드
    @PostMapping("/get")
    public ResponseEntity<ApiResponse<UserFullDataDto>> getRewardByType(
            @RequestBody RewardParams rewardParams
    ) {
        try {
            UserFullDataDto userData = rewardService.getRewardByType(rewardParams);
            if(userData == null) {
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",userData));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
