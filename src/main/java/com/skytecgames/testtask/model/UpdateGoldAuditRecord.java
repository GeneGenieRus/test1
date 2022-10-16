package com.skytecgames.testtask.model;


import java.util.Date;

public class UpdateGoldAuditRecord {
    private final Long userId;
    private final Long clanId;
    private final Integer amount;
    private final String reason;
    private final Date date;
    private final Integer resultAmount;

    public UpdateGoldAuditRecord(Long userId, Long clanId, Integer amount, String reason, Date date, Integer resultAmount) {
        this.userId = userId;
        this.clanId = clanId;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
        this.resultAmount = resultAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getClanId() {
        return clanId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public Date getDate() {
        return date;
    }

    public Integer getResultAmount() {
        return resultAmount;
    }

    @Override
    public String toString() {
        return "LogUpdateGoldDto{" +
                "userId=" + userId +
                ", clanId=" + clanId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                ", resultAmount=" + resultAmount +
                '}';
    }

}
