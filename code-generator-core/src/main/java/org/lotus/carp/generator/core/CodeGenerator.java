package org.lotus.carp.generator.core;

import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.table.Databse;
import org.lotus.carp.generator.base.table.Table;
import org.lotus.carp.generator.core.dto.EntityDto;
import org.lotus.carp.generator.core.processor.CodeProcessor;
import org.lotus.carp.generator.core.utils.GeneratorUtils;
import org.reflections.Reflections;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 9/6/2018
 * Time: 11:57 AM
 */
public class CodeGenerator {
    private static JpaConfig jpaConfig;
    private static List<Table> tableInfo = (new Databse()).gatherInfo();
    private String date;
    private String time;
    private List<CodeProcessor> processors = new ArrayList<>(10);

    public CodeGenerator() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = now.format(df);
        time = now.format(tf);
        Reflections reflections = new Reflections(jpaConfig.getScanForProcessorPackages());
        Set<Class<? extends CodeProcessor>> processes = reflections.getSubTypesOf(CodeProcessor.class);
        processes.stream().forEach(processor -> {
            try {
                processors.add(processor.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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

    public void renderCode() {
        Writer console = new PrintWriter(System.out);
        List<EntityDto> entityList = GeneratorUtils.collectEntityList(tableInfo, jpaConfig, date, time);
        processors.parallelStream().forEach(processor -> {
            entityList.parallelStream().forEach(item -> {
                String outPutDir = processor.codeDestination(jpaConfig);
                Path outPutPath = Paths.get(outPutDir);
                GeneratorUtils.makeSureFolderExist(outPutPath);
                Writer writer = null;
                try {
                    writer = new OutputStreamWriter(new FileOutputStream(processor.codeDestination(jpaConfig) + processor.fileName(jpaConfig, item)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Map params = new HashMap(5);
                params.put("entity", item);
                Freemarker.render(processor.codeTemplate(jpaConfig), params, writer);
                if (jpaConfig.getPrintOnConsole()) {
                    Freemarker.render(processor.codeTemplate(jpaConfig), params, console);
                }
            });
        });
    }

}
