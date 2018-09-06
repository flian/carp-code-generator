package org.lotus.carp.generator.core;

import freemarker.template.Template;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.table.Databse;
import org.lotus.carp.generator.base.table.Table;
import org.lotus.carp.generator.core.utils.GeneratorUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@Deprecated
public class JpaGenerator {
    private static JpaConfig jpaConfig;
    private static List<Table> tableInfo = (new Databse()).gatherInfo();
    private String date;
    private String time;
    private String pathSeparator = "/";

    public JpaGenerator() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = now.format(df);
        time = now.format(tf);
    }

    static {
        try {
            jpaConfig = Config.config(JpaConfig.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    public void rendEntityAndRepository() {
        rendEntityAndRepository(false);
    }

    public void rendEntityAndRepository(boolean printOnConsole) {
        Template entityTemplate = Freemarker.template(jpaConfig.getEntityTemplateName());
        Template repositoryTemplate = Freemarker.template(jpaConfig.getRepositoryTemplateName());
        Template serviceTemplate = Freemarker.template(jpaConfig.getServiceTemplateName());
        Template serviceImplTemplate = Freemarker.template(jpaConfig.getServiceImplTemplateName());
        GeneratorUtils.collectEntityList(tableInfo,jpaConfig,date,time).stream().forEach(item -> {
            Writer entityWriter = null;
            Writer repositoryWriter = null;
            Writer serviceWriter = null;
            Writer serviceImplWriter = null;
            if (printOnConsole) {
                entityWriter = repositoryWriter = serviceWriter = serviceImplWriter = new PrintWriter(System.out);
            } else {
                if (!printOnConsole) {
                    String entityOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getEntityPackage().replaceAll("\\.", pathSeparator) + pathSeparator;
                    String repositoryOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getRepositoryPackage().replaceAll("\\.", pathSeparator) + pathSeparator;
                    String serviceOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getServicePackage().replaceAll("\\.", pathSeparator) + pathSeparator;
                    String serviceImplOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getServiceImplPackage().replaceAll("\\.", pathSeparator) + pathSeparator;
                    Path entityOutPath = Paths.get(entityOutputDir);
                    Path repositoryPath = Paths.get(repositoryOutputDir);
                    Path servicePath = Paths.get(serviceOutputDir);
                    Path serviceImplPath = Paths.get(serviceImplOutputDir);
                    GeneratorUtils.makeSureFolderExist(entityOutPath);
                    GeneratorUtils.makeSureFolderExist(repositoryPath);
                    GeneratorUtils.makeSureFolderExist(servicePath);
                    GeneratorUtils.makeSureFolderExist(serviceImplPath);
                    try {
                        entityWriter = new OutputStreamWriter(new FileOutputStream(entityOutputDir + item.getEntityFileName()));
                        repositoryWriter = new OutputStreamWriter(new FileOutputStream(repositoryOutputDir + item.getRepositoryFileName()));
                        serviceWriter = new OutputStreamWriter(new FileOutputStream(serviceOutputDir + item.getServiceFileName()));
                        serviceImplWriter = new OutputStreamWriter(new FileOutputStream(serviceImplOutputDir + item.getServiceImplFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Map params = new HashMap(5);
            params.put("entity", item);
            Freemarker.render(entityTemplate, params, entityWriter);
            Freemarker.render(repositoryTemplate, params, repositoryWriter);
            Freemarker.render(serviceTemplate, params, serviceWriter);
            Freemarker.render(serviceImplTemplate, params, serviceImplWriter);
        });
    }
}
