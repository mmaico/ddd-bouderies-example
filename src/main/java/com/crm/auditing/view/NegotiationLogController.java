package com.crm.auditing.view;

import com.crm.auditing.application.NegotiationAuditingFacade;
import com.crm.auditing.domain.model.negotiation.NegotiationAudit;
import com.crm.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NegotiationLogController {


    @Autowired
    private NegotiationAuditingFacade application;


    @RequestMapping(value = "/negotiations/{negotiationId}/logs", method = RequestMethod.GET)
    public ModelAndView getListLogs(@PathVariable Long negotiationId,
                                    @PageableDefault(size = 1000) Pageable pageable, Model model) {

        Page<NegotiationAudit> result = application.findLogs(negotiationId, Pager.binding(pageable));

        model.addAttribute("negotiationLogs", result);
        return new ModelAndView("/negotiation/logs");
    }


}
