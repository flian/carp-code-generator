package org.lotus.carp.generator.core;

import lombok.Data;
import org.lotus.carp.generator.base.config.Prefix;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 9:59 AM
 */
@Data
public class JpaConfig implements Prefix {
    private String output;
    private Boolean useLombok;

    private String entityPackage;
    private String entitySufix;
    private String entityTemplateName;

    private String repositoryPackage;
    private String repositorySufix;
    private String repositoryTemplateName;

    @Override
    public String prefix() {
        return "jpa";
    }
}
