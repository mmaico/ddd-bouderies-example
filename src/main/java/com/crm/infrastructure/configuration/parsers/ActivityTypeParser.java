package com.crm.infrastructure.configuration.parsers;

import com.crm.infrastructure.entity.assistants.calendar.ActivityType;
import com.crm.infrastructure.entity.builders.ActivityTypeBuilder;
import com.google.common.collect.Lists;

import java.util.List;


public class ActivityTypeParser {




    public static List<ActivityType> getActivities() {
        List<ActivityType> items = Lists.newArrayList();

        items.add(ActivityTypeBuilder.create("E-Mail").build());
        items.add(ActivityTypeBuilder.create("Telefone").build());
        items.add(ActivityTypeBuilder.create("Atividade").build());
        items.add(ActivityTypeBuilder.create("Reuni&atilde;o").build());
        items.add(ActivityTypeBuilder.create("Outros").build());

        return items;
    }


}
