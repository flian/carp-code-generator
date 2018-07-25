package org.lotus.carp.generator.base.jdbc;

import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.config.JdbcConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 2:56 PM
 */
public class JDBCTemplate {
    public static void execute(JDBCExecutor executor) {
        Connection con = null;
        try {
            con = con();
            executor.execute(con);
        } finally {
            if (con != null) {
                try {
                    if (!con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Connection con() {
        JdbcConfig config = Config.jdbcConfig;
        try {
            Class.forName(config.getDriver());
            return DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't get jdbc connection!");
    }
}

