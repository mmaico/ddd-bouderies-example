package com.crm.register.domain;

import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.register.domain.contract.SaleableUnitDomainService;
import com.crm.register.infrastructure.validators.SaleableValidator;
import com.crm.infrastructure.helpers.HandlerErrors;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class SaleableUnitDomainServiceImpl implements SaleableUnitDomainService {

	@Autowired
	private SaleableValidator validator;


	Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

	{
		persistRules.put(RuleExpressionHelper.description("product.with.invalid.price"), (saleable) ->
						BigDecimal.ZERO.compareTo(saleable.getPrice()) > 0
				);

		persistRules.put(RuleExpressionHelper.description("product.with.invalid.infos"), (saleable) -> hasContraintViolated(saleable, validator));
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
