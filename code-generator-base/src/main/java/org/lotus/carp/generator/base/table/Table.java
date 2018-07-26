package org.lotus.carp.generator.base.table;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 2:47 PM
 */
@Data
public class Table {
    private String name;
    private String comment;
    private List<PrimaryKey> primaryKeys;
    private List<Column> columns;

    public boolean isColAutoincrementPrimaryKey(String colName) {
        if (!Strings.isNullOrEmpty(colName)) {
            if (null != primaryKeys && primaryKeys.size() > 0) {
                Optional<PrimaryKey> key = primaryKeys.stream().filter(pk -> pk.getName().equals(colName)).findFirst();
                if (key.isPresent()) {
                    Column col = findColumnByName(colName);
                    if (col.isAutoincrement()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Column findColumnByName(String colName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(colName), "colName should not be null");
        Optional<Column> col = columns.stream().filter(c -> c.getName().equals(colName)).findFirst();
        if (col.isPresent()) {
            return col.get();
        }
        throw new RuntimeException("column not exist. column name is:" + colName);
    }

    public Column firstPkColumn() {
        if (null != primaryKeys && primaryKeys.size() > 0) {
            return findColumnByName(primaryKeys.get(0).getName());
        }
        return null;
    }
}
