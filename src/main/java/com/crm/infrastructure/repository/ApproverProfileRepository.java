package com.crm.infrastructure.repository;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApproverProfileRepository extends BaseRepository<ApproverProfile, Long> {


    Optional<ApproverProfile> findByApprover(@Param("approver") User approver);

    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM ApproverProfile AS ap WHERE ap.available is true ")
    Boolean hasApprovers();

    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM ApproverProfile AS ap WHERE ap.available is true AND ap.approver <> :user ")
    Boolean hasApproversExcludeParam(@Param("user") User user);

}
