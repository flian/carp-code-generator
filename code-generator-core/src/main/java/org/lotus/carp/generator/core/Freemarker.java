package org.lotus.carp.generator.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.Data;
import org.lotus.carp.generator.base.config.Config;
import org.lotus.carp.generator.base.config.Prefix;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 9:58 AM
 */
@Data
public class Freemarker implements Prefix {
    private String dir;
    private String type;
    private static Configuration instance;
    private static Freemarker config = null;

    static {
        try {
            config = Config.config(Freemarker.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Freemarker() {

    }

    @Override
    public String prefix() {
        return "template";
    }

    private String templatePath() {
        return dir + "/" + type;
    }

    public static void render2Console(Template template, Map params) {
        render(template, params, new OutputStreamWriter(System.out));
    }

    public static void render(Template template, Map params, Writer out) {
        try {
            template.process(params, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Template template(String templateName) {
        try {
            return getInstance().getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Configuration getInstance() {
        if (null != instance) {
            return instance;
        }
        instance = new Configuration(Configuration.VERSION_2_3_27);
        // plain directory for it, but non-file-system sources are possible too:
        try {
            instance.setDirectoryForTemplateLoading((new ClassPathResource(config.templatePath())).getFile());

            instance.setDefaultEncoding("UTF-8");


            instance.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            instance.setLogTemplateExceptions(false);

            instance.setWrapUncheckedExceptions(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return instance;

    }
}
