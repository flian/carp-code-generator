package org.lotus.carp.generator.core.sample.repository;

import org.lotus.carp.generator.core.sample.entity.AccountAddressEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by carp-code-generator
 *
 * @author:   carp-code-generator
 * Date: 7/18/2018
 * Time: 4:21 PM
 */
@Repository
public interface AccountAddressRepository extends PagingAndSortingRepository<AccountAddressEntity,Long> {
}
