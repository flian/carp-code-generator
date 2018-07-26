package ${entity.repositoryPackage};

import ${entity.entityPackage}.${entity.name}${entity.entitySufix};
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
public interface ${entity.name}${entity.repositorySufix} extends PagingAndSortingRepository<${entity.name}${entity.entitySufix},${entity.pkType}> {
}
