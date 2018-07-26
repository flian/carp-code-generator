package ${entity.packageName};

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
public class ${entity.name} implements Serializable {
    private static final long serialVersionUID = ${entity.uuid};

<#list attributes as attr>
    <#if attr.isPK>@Id</#if>
    @Column(name = "${attr.columnName}")
    <#if attr.isAutoincrement>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    private ${attr.propertyType} ${attr.propertyName};
</#list>
}
