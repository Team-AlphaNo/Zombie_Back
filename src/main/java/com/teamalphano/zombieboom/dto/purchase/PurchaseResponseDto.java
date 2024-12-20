package com.teamalphano.zombieboom.dto.purchase;

import com.teamalphano.zombieboom.dto.user.UserDataDto;
import lombok.Data;

@Data
public class PurchaseResponseDto {
    private UserDataDto userDataDto;
    private String purchaseStatus;
}
