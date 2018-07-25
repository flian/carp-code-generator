package org.lotus.carp.generator.base.table;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 5:42 PM
 */
public class DatabseTest {

    @Test
    public void gatherInfo() {
        Databse db = new Databse();
        List<Table> info = db.gatherInfo();
        Assert.assertTrue(info.size() > 0);
    }
}