package com.skytecgames.testtask.repository;

import com.skytecgames.testtask.db.DataSource;
import com.skytecgames.testtask.model.UpdateGoldAuditRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class GoldAuditRepository {

    private final String SQL_INSERT =
            "insert into gold_audit(user_id, clan_id, income_amount, total, reason, date_time_updated) " +
                    "values (?, ?, ?, ?, ?, ?)";

    public void insert(UpdateGoldAuditRecord auditRecord) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {
            setParams(auditRecord, pst);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setParams(UpdateGoldAuditRecord auditRecord, PreparedStatement pst) throws SQLException {
        if (auditRecord.getUserId() != null) {
            pst.setLong(1, auditRecord.getUserId());
        } else {
            pst.setNull(1, Types.NUMERIC);
        }
        pst.setLong(2, auditRecord.getClanId());
        pst.setInt(3, auditRecord.getAmount());
        pst.setInt(4, auditRecord.getResultAmount());
        pst.setString(5, auditRecord.getReason());
        pst.setTimestamp(6, new java.sql.Timestamp(auditRecord.getDate().getTime()));
    }
}
