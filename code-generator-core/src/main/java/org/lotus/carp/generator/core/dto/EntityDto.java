package org.lotus.carp.generator.core.dto;

import com.google.common.base.Strings;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 11:36 AM
 */
@Data
public class EntityDto {
    private String tableName;
    private String className;
    private String date;
    private String time;
    private String author;
    private String uuid;
    private String entityPackage;
    private String repositoryPackage;
    private String entitySufix = "";
    private String repositorySufix = "";
    private String servicePackage;
    private String serviceSufix = "";
    private String serviceImplPackage;
    private String serviceImplSufix = "";
    private String pkType;
    private String comment;
    private List<EntityAttributeDto> attributes;

    public String getEntityFileName() {
        return className + Strings.nullToEmpty(entitySufix)+ ".java";
    }

    public String getRepositoryFileName() {
        return className +  Strings.nullToEmpty(repositorySufix) + ".java";
    }

    public String getServiceFileName() {
        return className +  Strings.nullToEmpty(serviceSufix) + ".java";
    }

    public String getServiceImplFileName() {
        return className +  Strings.nullToEmpty(serviceImplSufix) + ".java";
    }
    public String getName() {
        return className;
    }
}
