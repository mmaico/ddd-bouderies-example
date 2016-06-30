package com.crm.sales.infrastructure.persistence.hibernate;


import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalesOrderRepositoryH extends BaseRepository<SalesOrder, Long> {

    @Query("SELECT so FROM SalesOrder AS so WHERE so.client = :client")
    List<SalesOrder> getOrdersByClient(@Param("client") Client client);

    Optional<SalesOrder> findByProposal(@Param("domain") BusinessProposal proposal);

    @Query("SELECT so FROM SalesOrder AS so ORDER BY so.creationDate DESC")
    List<SalesOrder> findAllOrdered();
}
