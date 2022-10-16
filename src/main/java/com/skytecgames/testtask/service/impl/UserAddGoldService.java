package com.skytecgames.testtask.service.impl;

import com.skytecgames.testtask.model.UpdateGoldRequest;
import com.skytecgames.testtask.service.ClanService;

//example of use
public class UserAddGoldService {

    private final ClanService clanService;

    public UserAddGoldService(ClanService clanService) {
        this.clanService = clanService;
    }

    public void addGoldToClan(long userId, long clanId, int gold) {
        clanService.updateGold(
                new UpdateGoldRequest(userId, clanId, gold, "UserAddGoldService: addGoldToClan"));
    }
}
