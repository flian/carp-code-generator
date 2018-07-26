package org.lotus.carp.generator.core.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 11:37 AM
 */
@Data
public class EntityAttributeDto {
    private String columnName;
    private String propertyName;
    private String propertyType;
    private boolean pk;
    private boolean autoincrement;
    private String comment;
}
