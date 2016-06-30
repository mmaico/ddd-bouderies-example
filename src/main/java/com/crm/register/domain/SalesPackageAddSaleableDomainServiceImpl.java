package com.crm.register.domain;

import com.crm.infrastructure.entity.saleable.SaleableType;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.register.application.contract.saleable.SaleableApplication;
import com.crm.register.domain.contract.SalesPackageAddSaleableDomainService;
import com.crm.infrastructure.helpers.HandlerErrors;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalesPackageAddSaleableDomainServiceImpl implements SalesPackageAddSaleableDomainService {

	@Autowired
	private SaleableApplication application;

	Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

	{
		persistRules.put(
				RuleExpressionHelper.description("salespackage.not.exists"), (salespackage) -> salespackage == null || salespackage.isNew() ||
				!application.getOne(salespackage.getId()).isPresent()
		);

		persistRules.put(RuleExpressionHelper.description("salespackage.not.permit.add.package"), (salespackage) ->
				application.getOne(salespackage.getId()).get().getType() == SaleableType.PACKAGE
		);
	}


	@Override
	public void checkBusinessRulesFor(SaleableUnit saleableUnit) {

		Set<String> violations = persistRules.entrySet()
				.stream()
				.filter(e -> e.getValue().check(saleableUnit))
				.map(Map.Entry::getKey).collect(Collectors.toSet());

		HandlerErrors.hasErrors(violations).throwing(ValidationException.class);
	}



}
