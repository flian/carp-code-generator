package org.lotus.carp.generator.core.sample.repository;

import ${entity.entityPackageName}.${entity.name};
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ${entity.author}
 *
 * @author:  ${entity.author}
 * Date: ${entity.date}
 * Time: ${entity.time}
 */
@Repository
public interface ${entity.entityName}${entity.repositorySufix} extends PagingAndSortingRepository<${entity.name}${entity.entitySufix},${entity.pkType}> {
}
