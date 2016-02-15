package simplemapper;

import java.util.HashMap;
import java.util.Map;

class MapperConfiguration<TS, TD>
{
	private CustomMapping<TS, TD> customMapping;
	private Map<String, FieldResolver<TS, TD>> fieldResolvers = new HashMap<>();
	private Map<String, FieldMapping<TS, TD>> fieldMappings= new HashMap<>();

	public MapperConfiguration<TS, TD> mapUsing(Class<? extends CustomMapping<TS, TD>> t) throws InstantiationException, IllegalAccessException
	{
		setCustomMapping(t.newInstance());
		return this;
	}

	public TD translate(TS source, TD destination) throws MapperException
	{
		if (customMapping != null) customMapping.map(source, destination);
		return destination;
	}

	public CustomMapping<TS, TD> getCustomMapping() {
		return customMapping;
	}

	public void setCustomMapping(CustomMapping<TS, TD> customMapping) {
		this.customMapping = customMapping;
	}
	
	public MapperConfiguration forField(String sourceFieldName, FieldResolver<TS, TD> fieldResolver){
		fieldResolvers.put(sourceFieldName.toLowerCase(), fieldResolver);
		return this;
	}

	public MapperConfiguration forField(String sourceFieldName, FieldMapping<TS, TD> fieldMapping){
		fieldMappings.put(sourceFieldName, fieldMapping);
		return this;
	}
	
	public FieldResolver<TS, TD> getFieldResolver(String fieldName){
		return fieldResolvers.get(fieldName.toLowerCase());
	}

	public FieldMapping<TS, TD> getFieldMapper(String fieldName){
		return fieldMappings.get(fieldName);
	}
}
