package org.lotus.carp.generator.core;

import org.lotus.carp.generator.base.config.Config;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 9:59 AM
 */
public class JpaGenerator {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        FreemarkerConfig freemarkerConfig = Config.config(FreemarkerConfig.class);
        JpaConfig jpaConfig = Config.config(JpaConfig.class);
    }
}
