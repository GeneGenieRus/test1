package com.skytecgames.testtask.service.impl;

import com.skytecgames.testtask.model.UpdateGoldAuditRecord;
import com.skytecgames.testtask.repository.GoldAuditRepository;
import com.skytecgames.testtask.service.AuditService;

import java.util.concurrent.ExecutorService;

public class AsyncAuditService implements AuditService {

    private final ExecutorService executorService;
    private final GoldAuditRepository goldAuditRepository;

    public AsyncAuditService(ExecutorService executorService,
                             GoldAuditRepository goldAuditRepository) {
        this.executorService = executorService;
        this.goldAuditRepository = goldAuditRepository;
    }

    @Override
    public void logUpdateGold(UpdateGoldAuditRecord updateGoldAuditRecord) {
            executorService.submit(
                    () -> goldAuditRepository.insert(updateGoldAuditRecord));
    }
}
