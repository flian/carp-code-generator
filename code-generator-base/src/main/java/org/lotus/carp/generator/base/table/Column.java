package org.lotus.carp.generator.base.table;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 2:47 PM
 */
@Data
public class Column {
    private String name;
    private String comment;
    private String type;
    private int dataType;
    private boolean autoincrement;
    private int pos;
}
