package org.lotus.carp.generator.base.config;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 4:04 PM
 */
@Data
public class GeneratorConfig implements Prefix {
    private Boolean useLombok;

    @Override
    public String prefix() {
        return "carp";
    }
}
