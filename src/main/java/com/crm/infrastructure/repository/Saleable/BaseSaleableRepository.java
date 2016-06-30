package com.crm.infrastructure.repository.Saleable;

import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseSaleableRepository<T extends SaleableUnit> extends BaseRepository<T, Long> {

}
