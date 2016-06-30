package com.crm.register.view.users;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.UserBuilder;
import com.crm.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.register.application.contract.UserApproverProfileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApproverProfileController {

    @Autowired
    private UserApproverProfileApplication application;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;



    @RequestMapping(value = "/users/{userId}/approver-profile/save", method = RequestMethod.POST)
    public  @ResponseBody void save(@ModelAttribute ApproverProfile approverProfile, @PathVariable Long userId) {

        User user = UserBuilder.createUser(userId).build();
        approverProfile.setApprover(user);
        approverProfile.getFields().add("available");

        application.register(approverProfile);

    }




}
