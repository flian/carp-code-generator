package org.lotus.carp.generator.base.table;

import lombok.Data;

import java.util.List;

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
    private List<Column> columns;
}
