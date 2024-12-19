package com.teamalphano.zombieboom.dto.purchase;

import com.teamalphano.zombieboom.model.user.UserData;
import lombok.Data;

@Data
public class PurchaseResponseDto {
    private UserData userData;
    private String purchaseStatus;
}
