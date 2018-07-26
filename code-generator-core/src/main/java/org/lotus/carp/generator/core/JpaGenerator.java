package org.lotus.carp.generator.core;

import freemarker.template.Template;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.table.Databse;
import org.lotus.carp.generator.base.table.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

    public void rendEntity() {
        try {
            Writer out = new FileWriter(new File(jpaConfig.getEntityPackage()));
            rendEntity(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rendEntity(Writer out) {
        Template entityTemplate = Freemarker.template(jpaConfig.getEntityTemplateName());
        Map params = new HashMap();
        params.put("tableInfo", tableInfo);
        Freemarker.render(entityTemplate, params, out);
    }

    public void rendRepository() {
        try {
            Writer out = new FileWriter(new File(jpaConfig.getRepositoryPackage()));
            rendRepository(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rendRepository(Writer out) {
        Template entityTemplate = Freemarker.template(jpaConfig.getRepositoryTemplateName());
        Map params = new HashMap();
        params.put("tableInfo", tableInfo);
        Freemarker.render(entityTemplate, params, out);
    }
}
