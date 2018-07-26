package org.lotus.carp.generator.base.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 3:48 PM
 */
@Slf4j
public class Config {
    private static Properties generator;
    public static JdbcConfig jdbcConfig;
    public static GeneratorConfig generatorConfig;

    static {
        generator = new Properties();
        try {
            generator.load(Config.class.getClassLoader().getResourceAsStream("generator.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            generator.load(Config.class.getClassLoader().getResourceAsStream("generatorExtend.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jdbcConfig = config(JdbcConfig.class);
            generatorConfig = config(GeneratorConfig.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static String get(String key) {
        return generator.getProperty(key);
    }

    /**
     * 初始化配置对象
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends Prefix> T config(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Arrays.stream(clazz.getDeclaredFields()).forEach(f -> {
            String val = get(obj.prefix() + "." + f.getName());
            f.setAccessible(true);
            Class type = f.getType();
            try {
                if (type.isAssignableFrom(Boolean.class)) {
                    f.set(obj, Boolean.valueOf(val));
                } else {
                    f.set(obj, val);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return obj;
    }
}
