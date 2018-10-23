package org.lotus.carp.generator.base.jdbc;

import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.config.JdbcConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            Properties props =new Properties();
            props.setProperty("user", config.getUserName());
            props.setProperty("password", config.getPassword());
            //设置可以获取remarks信息
            props.setProperty("remarks", "true");
            //设置可以获取tables remarks信息
            props.setProperty("useInformationSchema", "true");
            Connection conn = DriverManager.getConnection(config.getUrl(), props);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't get jdbc connection!");
    }
}

