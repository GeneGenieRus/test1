package com.skytecgames.testtask.model;


public class UpdateGoldRequest {
    private final Long userId;
    private final Long clanId;
    private final Integer amount;
    private final String reason;

    public UpdateGoldRequest(Long userId, Long clanId, Integer amount, String reason) {
        this.userId = userId;
        this.clanId = clanId;
        this.amount = amount;
        this.reason = reason;
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

    @Override
    public String toString() {
        return "UpdateGoldRequest{" +
                "userId=" + userId +
                ", clanId=" + clanId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                '}';
    }
}
