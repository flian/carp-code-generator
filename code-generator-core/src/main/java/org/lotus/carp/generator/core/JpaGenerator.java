package org.lotus.carp.generator.core;

import freemarker.template.Template;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.table.Databse;
import org.lotus.carp.generator.base.table.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 9:59 AM
 */
public class JpaGenerator {
    private static JpaConfig jpaConfig;
    private static List<Table> tableInfo = (new Databse()).gatherInfo();

    static {
        try {
            jpaConfig = Config.config(JpaConfig.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void rendEntity2Console() {
        Template entityTemplate = Freemarker.template(jpaConfig.getEntityTemplateName());
        Map params = new HashMap();
        params.put("tableInfo", tableInfo);
        Freemarker.render2Console(entityTemplate, params);
    }

    public void rendRepository2Console() {
        Template entityTemplate = Freemarker.template(jpaConfig.getRepositoryTemplateName());
        Map params = new HashMap();
        params.put("tableInfo", tableInfo);
        Freemarker.render2Console(entityTemplate, params);
    }
}
