package com.skytecgames.testtask.service;

import com.skytecgames.testtask.model.UpdateGoldAuditRecord;

public interface AuditService {

    void logUpdateGold(UpdateGoldAuditRecord updateGoldAuditRecord);
}
