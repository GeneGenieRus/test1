package com.skytecgames.testtask.service.impl;

import com.skytecgames.testtask.model.Clan;
import com.skytecgames.testtask.model.UpdateGoldAuditRecord;
import com.skytecgames.testtask.model.UpdateGoldRequest;
import com.skytecgames.testtask.service.AuditService;
import com.skytecgames.testtask.service.ClanService;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ClanServiceImpl implements ClanService {

    private final AuditService auditService;
    private final Map<Long, Clan> clans = Map.of(
            1L, new Clan(1, "clan", new AtomicInteger()));

    public ClanServiceImpl(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public Clan get(long clanId) {
        return clans.get(clanId);
    }

    @Override
    public int updateGold(UpdateGoldRequest updateGoldRequest) {
        int updatedSum = clans.get(updateGoldRequest.getClanId())
                .getGold()
                .addAndGet(updateGoldRequest.getAmount());
        auditService.logUpdateGold(buildAuditRecord(updateGoldRequest, updatedSum));
        return updatedSum;
    }

    private UpdateGoldAuditRecord buildAuditRecord(UpdateGoldRequest updateGoldRequest, int updatedSum) {
        return new UpdateGoldAuditRecord(
                updateGoldRequest.getUserId(),
                updateGoldRequest.getClanId(),
                updateGoldRequest.getAmount(),
                updateGoldRequest.getReason(),
                new Date(),
                updatedSum
        );
    }
}
