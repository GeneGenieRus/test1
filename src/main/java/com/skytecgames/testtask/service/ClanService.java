package com.skytecgames.testtask.service;

import com.skytecgames.testtask.model.Clan;
import com.skytecgames.testtask.model.UpdateGoldRequest;

public interface ClanService {

    Clan get(long clanId);

    int updateGold(UpdateGoldRequest updateGoldRequest);
}
