package org.lotus.carp.generator.core.dto;

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
    private String entitySufix;
    private String repositorySufix;
    private String pkType;
    private String comment;
    private List<EntityAttributeDto> attributes;

    public String getEntityFileName() {
        return className + entitySufix + ".java";
    }

    public String getRepositoryFileName() {
        return className + repositorySufix + ".java";
    }

    public String getName() {
        return className;
    }
}
