package com.crm.infrastructure.repository.predicates;

import com.crm.infrastructure.entity.person.PersonProfile;
import com.crm.infrastructure.entity.person.QPerson;
import com.crm.infrastructure.helpers.Filter;
import com.crm.infrastructure.helpers.FilterAggregator;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

import java.util.Collection;

public class PersonPredicate {

	public static Predicate findByFilters(FilterAggregator filter) {
		QPerson qPerson = QPerson.person;
		
		BooleanExpression expression = qPerson.id.isNotNull();
		
		Filter<PersonProfile> profileFilter = filter.findFilter("profile");
		Filter<Collection<PersonProfile>> profilesFilter = filter.findFilter("profiles");
		Filter<Boolean> statusFilter = filter.findFilter("status");
		
		if (!profileFilter.isNullObject()) {
			expression = expression.and(qPerson.profile.eq(profileFilter.getObject()));
		}
		
		if (!profilesFilter.isNullObject()) {
            expression = expression.and(qPerson.profile.in(profilesFilter.getObject()));
		}
		
		if (!statusFilter.isNullObject()) {
			expression = expression.and(qPerson.active.eq(statusFilter.getObject()));
		}
		
		return expression;
	}
	
	public static OrderSpecifier<String> orderByName() {
		return QPerson.person.name.asc();
	}
}

