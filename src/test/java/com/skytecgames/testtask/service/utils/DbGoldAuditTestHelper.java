package com.skytecgames.testtask.service.utils;

import com.skytecgames.testtask.db.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbGoldAuditTestHelper {

    public static int getCount() {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("select count(*) from gold_audit");
             ResultSet resultSet = pst.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static int getSumOfAmounts() {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("select sum(income_amount) from gold_audit");
             ResultSet resultSet = pst.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
