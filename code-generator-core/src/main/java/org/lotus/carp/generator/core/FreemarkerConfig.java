package org.lotus.carp.generator.core;

import lombok.Data;
import org.lotus.carp.generator.base.config.Prefix;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 9:58 AM
 */
@Data
public class FreemarkerConfig implements Prefix {
    private String dir;
    private String type;

    @Override
    public String prefix() {
        return "template";
    }
}
