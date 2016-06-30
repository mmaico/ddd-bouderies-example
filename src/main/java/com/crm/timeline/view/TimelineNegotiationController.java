package com.crm.timeline.view;

import com.crm.timeline.application.TimelineFacade;
import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.NegotiationBuilder;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TimelineNegotiationController {


    @Autowired
    private TimelineFacade timelineFacade;



    @RequestMapping(value = "/negotiations/{negotiationId}/activities", method = RequestMethod.GET)
    public ModelAndView getTimeline(@PathVariable Long negotiationId, Model model) {

        Negotiation negotiation = NegotiationBuilder.createNegotiation(negotiationId).build();
        TimelineNegotiation timelineNegotiation = timelineFacade.register(negotiation);

        model.addAttribute(timelineNegotiation);
        return new ModelAndView("/timeline/negotiation");
    }


}
