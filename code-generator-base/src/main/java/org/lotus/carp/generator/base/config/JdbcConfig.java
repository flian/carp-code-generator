package org.lotus.carp.generator.base.config;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 4:03 PM
 */
@Data
public class JdbcConfig implements Prefix {
    private String driver;
    private String url;
    private String userName;
    private String password;

    @Override
    public String prefix() {
        return "jdbc";
    }
}
