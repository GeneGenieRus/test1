package com.skytecgames.testtask.service;

import com.skytecgames.testtask.db.DataSource;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class BaseTest {

    @BeforeAll
    static void beforeAll() throws SQLException, IOException {
        initDb();
    }

    private static void initDb() throws IOException, SQLException {
        String initSql = new String(BaseTest.class.getClassLoader().getResourceAsStream("init.sql").readAllBytes());
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(initSql);) {
            pst.execute();
        }
    }
}