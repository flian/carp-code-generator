package org.lotus.carp.generator.core.processor;

import freemarker.template.Template;
import org.lotus.carp.generator.core.JpaConfig;
import org.lotus.carp.generator.core.dto.EntityDto;

import java.util.Map;

/**
 * 定义通用的模板生成
 *
 * @author: Foy Lian
 * Date: 9/6/2018
 * Time: 11:45 AM
 */
public interface CodeProcessor {
    String pathSeparator = "/";

    /**
     * 获取生成代码的模板
     *
     * @param config 全局配置
     * @return
     */
    Template codeTemplate(JpaConfig config);

    /**
     * 获取生成代码的目标路径
     *
     * @param config 全局配置
     * @return
     */
    String codeDestination(JpaConfig config);

    /**
     * 获取生成的文件名
     *
     * @param config
     * @return
     */
    String fileName(JpaConfig config, EntityDto entity);

    /**
     *  生成也页面前，给自定义处理器添加更多参数的机会
     * @param params 给页面的参数map
     */
    default void addationParamsBeforeRenderPage(Map params) {

    }
}
