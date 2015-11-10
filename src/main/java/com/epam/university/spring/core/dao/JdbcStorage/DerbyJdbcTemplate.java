package com.epam.university.spring.core.dao.JdbcStorage;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-10
 * Time: 10:58
 */
public class DerbyJdbcTemplate extends JdbcTemplate {

    public DerbyJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public void destroy() {
        try {
            DriverManager.getConnection("jdbc:derby:memory:AuditoriumManagementDB;drop=true");
        } catch (SQLException e) {
            if (( (e.getErrorCode() == 45000)
                    && ("08006".equals(e.getSQLState()) ))) {
                // we got the expected exception
                System.out.println("Derby dropped down normally");
                // Note that for single database shutdown, the expected
                // SQL state is "08006", and the error code is 45000.
            } else {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                System.err.println("Derby did not drop down normally");
                e.printStackTrace(System.err);
            }
        }
    }
}
