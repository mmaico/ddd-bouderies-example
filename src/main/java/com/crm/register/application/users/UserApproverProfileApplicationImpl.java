package com.crm.register.application.users;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.UserApproverProfileRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.UserApplication;
import com.crm.register.application.contract.UserApproverProfileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApproverProfileApplicationImpl extends BaseModelServiceImpl<ApproverProfile> implements UserApproverProfileApplication {

    @Autowired
    private UserApproverProfileRepository profileRepository;

    @Autowired
    private UserApplication userApplication;


    @Override
    public Optional<ApproverProfile> register(ApproverProfile userProfile) {

        if (userProfile.getApprover() == null || userProfile.getApprover().isNew()) {
            return Optional.empty();
        }

        Optional<User> userLoaded = userApplication.getOne(userProfile.getApprover().getId());

        ApproverProfile approverProfileSaved = super.save(userProfile);

        if (userLoaded.isPresent()) {
            userLoaded.get().setApproverProfile(approverProfileSaved);
        }

        return Optional.of(approverProfileSaved);
    }



    public BaseRepository<ApproverProfile, Long> getRepository() {
        return profileRepository;
    }

}
