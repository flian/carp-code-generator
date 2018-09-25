package org.lotus.carp.generator.core.utils;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.lotus.carp.generator.base.table.Column;
import org.lotus.carp.generator.base.table.Table;
import org.lotus.carp.generator.core.JpaConfig;
import org.lotus.carp.generator.core.dto.EntityAttributeDto;
import org.lotus.carp.generator.core.dto.EntityDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.sql.Types.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 9/6/2018
 * Time: 4:22 PM
 */
@Slf4j
public class GeneratorUtils {

    public static List<EntityDto> collectEntityList(List<Table> tableInfo, JpaConfig jpaConfig, String date, String time) {
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
            entityDto.setServicePackage(jpaConfig.getServicePackage());
            entityDto.setServiceImplPackage(jpaConfig.getServiceImplPackage());
            entityDto.setServiceSufix(jpaConfig.getServiceSufix());
            entityDto.setServiceImplSufix(jpaConfig.getServiceImplSufix());

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

    public static void makeSureFolderExist(Path out) {
        if (!Files.exists(out)) {
            try {
                Files.createDirectories(out);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static String map2JavaType(Column column) {
        if (null == column) {
            log.error("WTF? column is null?");
            return String.class.getSimpleName();
        }
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

    private static Long uuid() {
        return UUID.randomUUID().getMostSignificantBits();
    }

    private static String toClassName(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String toPropertyName(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toLowerCase());
    }
}
