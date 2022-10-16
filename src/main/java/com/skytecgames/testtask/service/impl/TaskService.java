package com.skytecgames.testtask.service.impl;

import com.skytecgames.testtask.model.UpdateGoldRequest;
import com.skytecgames.testtask.service.ClanService;

//example of use
public class TaskService {

    private final ClanService clanService;

    public TaskService(ClanService clanService) {
        this.clanService = clanService;
    }

    void completeTask(long clanId, long taskId) {
        // ...

        // if (success)
        {
            int gold = 100;
            clanService.updateGold(
                    new UpdateGoldRequest(null, clanId, gold, "TaskService: taskId=" + taskId));
        }
    }
}
