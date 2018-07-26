package ${entity.entityPackage};

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created  by ${entity.author}
 *
 * @author: ${entity.author}
 * Date: ${entity.date}
 * Time: ${entity.time}
 */
@Entity
@Table(name = "${entity.tableName}")
@Data
public class ${entity.name}${entity.entitySufix} implements Serializable {
    private static final long serialVersionUID = ${entity.uuid};

<#list entity.attributes as attr>
    <#if attr.hasComment>
    /**
     *${attr.comment}
     */
    </#if>
    <#if attr.pk>@Id</#if>
    @Column(name = "${attr.columnName}")
    <#if attr.autoincrement>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    private ${attr.propertyType} ${attr.propertyName};
</#list>
}
