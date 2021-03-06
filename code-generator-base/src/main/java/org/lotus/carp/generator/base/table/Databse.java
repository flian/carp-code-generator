package org.lotus.carp.generator.base.table;

import lombok.extern.slf4j.Slf4j;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.jdbc.JDBCTemplate;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 5:15 PM
 */
@Slf4j
public class Databse {
    public List<Table> gatherInfo() {
        final List<Table> result = new ArrayList<>(100);
        JDBCTemplate.execute(con -> {
            try {
                DatabaseMetaData md = con.getMetaData();
                String[] types = {"TABLE", "VIEW"};
                String catalog = con.getCatalog() == null ? null : con.getCatalog();
                ResultSet tablesRs = md.getTables(catalog, Config.jdbcConfig.getUserName(), "%%", types);

                while (tablesRs.next()) {
                    String tableName = tablesRs.getString("TABLE_NAME");
                    String tableComment = tablesRs.getString("REMARKS");

                    Table table = new Table();
                    table.setName(tableName);
                    table.setComment(tableComment);
                    //extract primaryKeys
                    List<PrimaryKey> primaryKeys = new ArrayList<>();
                    ResultSet primaryKeysRs = md.getPrimaryKeys(con.getCatalog(), con.getSchema(), tableName);
                    while (primaryKeysRs.next()) {
                        PrimaryKey key = new PrimaryKey();
                        key.setName(primaryKeysRs.getString("COLUMN_NAME"));
                        key.setSeq(primaryKeysRs.getInt("KEY_SEQ"));
                        primaryKeys.add(key);
                    }
                    table.setPrimaryKeys(primaryKeys);
                    primaryKeysRs.close();

                    //extract columns
                    List<Column> columns = new ArrayList<>();
                    ResultSet columnsRs = md.getColumns(con.getCatalog(), "%%", tableName, "%%");
                    while (columnsRs.next()) {
                        Column col = new Column();
                        col.setName(columnsRs.getString("COLUMN_NAME"));
                        col.setType(columnsRs.getString("TYPE_NAME"));
                        col.setComment(columnsRs.getString("REMARKS"));
                        col.setDataType(columnsRs.getInt("DATA_TYPE"));
                        col.setAutoincrement(columnsRs.getBoolean("IS_AUTOINCREMENT"));
                        col.setPos(columnsRs.getInt("ORDINAL_POSITION"));
                        columns.add(col);
                    }
                    table.setColumns(columns);
                    result.add(table);
                    columnsRs.close();
                }
                tablesRs.close();
            } catch (SQLException e) {
                log.error("exception get database meta.", e);
            }
        });
        return result;
    }

}
