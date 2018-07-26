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
    private String name;
    private String date;
    private String time;
    private String author;
    private String uuid;
    private String packageName;
    private String entitySufix;
    private String repositorySufix;
    private String pkType;
    private List<EntityAttributeDto> attributes;
    public String getEntityFileName(){
        return name+entitySufix+".java";
    }
    public String getRepositoryFileName(){
        return name+repositorySufix+".java";
    }
}
