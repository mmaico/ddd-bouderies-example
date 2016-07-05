
package com.crm.infrastructure.helpers.businessmodel.converters;


public interface AttributeConverter<X,Y> {

	Y convertToEntityAttribute(X attribute);


	X convertToBusinessModel(Y dbData);
}
