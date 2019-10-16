package org.improving.tag.database;

import java.util.Arrays;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.improving.tag.items.UniqueItems;

@Converter(autoApply = true)
public class UniqueItemsConverter implements AttributeConverter<UniqueItems, String> {

	@Override
	public String convertToDatabaseColumn(UniqueItems item) {
		if (item == null) {
			return null;
		}
		return item.getName();
	}

	@Override
	public UniqueItems convertToEntityAttribute(String name) {
		if (null != name) {
          return Arrays
                  .stream(UniqueItems.values())
                  .filter(item -> item.getName().equals(name))
                  .findFirst()
                  .orElse(null);
		}
		
		return UniqueItems.NOTHING;
	}
}