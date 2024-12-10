package com.teamalphano.zombieboom.controller.rank;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.rank.RankInsertDto;
import com.teamalphano.zombieboom.model.rank.WorldRank;
import com.teamalphano.zombieboom.service.rank.RankService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/world")
    public ResponseEntity<ApiResponse<List<WorldRank>>> getWorldRankList(
            @RequestParam(value = "type", required = false) Integer type
    ){
        try{
            List<WorldRank> data= rankService.getWorldRankList(type);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<String>> insertRank(
            @RequestBody RankInsertDto rankInsertDto
    ){
        try {
            System.out.println(rankInsertDto.toString());
            String insert = rankService.insertRank(rankInsertDto);
            return ResponseEntity.ok(new ApiResponse<>(200, "Success", insert));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Internal server error", null));
        }
    }
}
