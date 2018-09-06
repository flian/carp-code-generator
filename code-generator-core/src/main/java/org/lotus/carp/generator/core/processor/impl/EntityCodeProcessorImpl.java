package org.lotus.carp.generator.core.processor.impl;

import freemarker.template.Template;
import org.lotus.carp.generator.core.Freemarker;
import org.lotus.carp.generator.core.JpaConfig;
import org.lotus.carp.generator.core.dto.EntityDto;
import org.lotus.carp.generator.core.processor.CodeProcessor;

/**
 * 产生Entity实体类
 *
 * @author: Foy Lian
 * Date: 9/6/2018
 * Time: 11:53 AM
 */
public class EntityCodeProcessorImpl implements CodeProcessor {
    /**
     * 获取生成代码的模板
     *
     * @param config 全局配置
     * @return
     */
    @Override
    public Template codeTemplate(JpaConfig config) {
        return Freemarker.template(config.getEntityTemplateName());
    }

    /**
     * 获取生成代码的目标路径
     *
     * @param config 全局配置
     * @return
     */
    @Override
    public String codeDestination(JpaConfig config) {
        return config.getOutput() + pathSeparator + config.getEntityPackage().replaceAll("\\.", pathSeparator) + pathSeparator;
    }

    /**
     * 获取生成的文件名
     *
     * @param config
     * @param entity
     * @return
     */
    @Override
    public String fileName(JpaConfig config, EntityDto entity) {
        return entity.getRepositoryFileName();
    }
}
