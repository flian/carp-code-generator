package org.lotus.carp.generator.base.jdbc;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 2:57 PM
 */
@FunctionalInterface
public interface JDBCExecutor {
    void execute(Connection con);
}
