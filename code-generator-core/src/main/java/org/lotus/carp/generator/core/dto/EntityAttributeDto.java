package org.lotus.carp.generator.core.dto;

import com.google.common.base.CaseFormat;
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

    public boolean getHasComment() {
        return comment == null || comment.length() == 0 ? false : true;
    }

    public String getGetterMethodName() {
        return "get" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, propertyName.toLowerCase());
    }

    public String getSetterMethodname() {
        return "set" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, propertyName.toLowerCase());
    }
}
