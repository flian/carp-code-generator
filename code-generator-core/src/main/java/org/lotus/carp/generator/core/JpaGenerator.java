package org.lotus.carp.generator.core;

import com.google.common.base.CaseFormat;
import freemarker.template.Template;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.table.Column;
import org.lotus.carp.generator.base.table.Databse;
import org.lotus.carp.generator.base.table.Table;
import org.lotus.carp.generator.core.dto.EntityAttributeDto;
import org.lotus.carp.generator.core.dto.EntityDto;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Types.*;

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

    private void makeSureFolderExist(Path out) {
        if (!Files.exists(out)) {
            try {
                Files.createDirectories(out);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void rendEntityAndRepository() {
        rendEntityAndRepository(false);
    }

    public void rendEntityAndRepository(boolean printOnConsole) {
        Template entityTemplate = Freemarker.template(jpaConfig.getEntityTemplateName());
        Template repositoryTemplate = Freemarker.template(jpaConfig.getRepositoryTemplateName());

        colectionEntityList().stream().forEach(item -> {
            Writer entityWriter = null;
            Writer repositoryWriter = null;
            if (printOnConsole) {
                entityWriter = repositoryWriter = new PrintWriter(System.out);
            } else {
                if (!printOnConsole) {
                    String entityOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getEntityPackage().replaceAll("\\.", pathSeparator) + pathSeparator;
                    String repositoryOutputDir = jpaConfig.getOutput() + pathSeparator + jpaConfig.getRepositoryPackage().replaceAll("\\.", pathSeparator) + pathSeparator;

                    Path entityOutPath = Paths.get(entityOutputDir);
                    Path repositoryPath = Paths.get(repositoryOutputDir);
                    makeSureFolderExist(entityOutPath);
                    makeSureFolderExist(repositoryPath);
                    try {
                        entityWriter = new OutputStreamWriter(new FileOutputStream(entityOutputDir + item.getEntityFileName()));
                        repositoryWriter = new OutputStreamWriter(new FileOutputStream(repositoryOutputDir + item.getRepositoryFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Map params = new HashMap();
            params.put("entity", item);
            Freemarker.render(entityTemplate, params, entityWriter);
            Freemarker.render(repositoryTemplate, params, repositoryWriter);
        });
    }

    private List<EntityDto> colectionEntityList() {
        final List<EntityDto> result = new ArrayList<>();
        tableInfo.forEach(table -> {
            EntityDto entityDto = new EntityDto();
            entityDto.setAuthor(jpaConfig.getAuthor());
            entityDto.setDate(date);
            entityDto.setTime(time);
            entityDto.setEntitySufix(jpaConfig.getEntitySufix());
            entityDto.setRepositorySufix(jpaConfig.getRepositorySufix());
            entityDto.setTableName(table.getName());
            entityDto.setUuid("" + uuid() + "L");
            entityDto.setEntityPackage(jpaConfig.getEntityPackage());
            entityDto.setRepositoryPackage(jpaConfig.getRepositoryPackage());
            String className = toClassName(table.getName());
            entityDto.setClassName(className);
            entityDto.setComment(table.getComment());
            entityDto.setPkType(map2JavaType(table.firstPkColumn()));
            entityDto.setAttributes(table.getColumns().stream().map(column -> {
                EntityAttributeDto entityAttributeDto = new EntityAttributeDto();
                entityAttributeDto.setAutoincrement(column.isAutoincrement());
                entityAttributeDto.setPk(table.isColAutoincrementPrimaryKey(column.getName()));
                entityAttributeDto.setColumnName(column.getName());
                entityAttributeDto.setPropertyName(toPropertyName(column.getName()));
                entityAttributeDto.setPropertyType(map2JavaType(column));
                entityAttributeDto.setComment(column.getComment());
                return entityAttributeDto;
            }).collect(Collectors.toList()));
            result.add(entityDto);
        });
        return result;
    }


    private Long uuid() {
        return UUID.randomUUID().getMostSignificantBits();
    }

    private String map2JavaType(Column column) {
        switch (column.getDataType()) {
            case BIT:
            case TINYINT:
            case SMALLINT:
            case INTEGER:
                return Integer.class.getSimpleName();
            case BIGINT:
                return Long.class.getSimpleName();
            case FLOAT:
            case REAL:
            case DOUBLE:
            case NUMERIC:
                return Double.class.getSimpleName();
            case DECIMAL:
                return BigDecimal.class.getSimpleName();
            case DATE:
            case TIME:
            case TIMESTAMP:
                return Date.class.getSimpleName();
            case CHAR:
            case VARCHAR:
            case LONGVARCHAR:
                return String.class.getSimpleName();
            default:
                return String.class.getSimpleName();
        }
    }

    private String toClassName(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private String toPropertyName(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toLowerCase());
    }
}
