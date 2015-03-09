package simplemapper;

import java.util.HashMap;
import java.util.Map;

class MapperConfiguration<TS, TD>
{
	private CustomMapping<TS, TD> customMapping;
	private Map<String, FieldResolver<?, ?>> fieldResolvers = new HashMap<String, FieldResolver<?, ?>>();
	
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
	
	public void forField(String sourceFieldName, FieldResolver<?, ?> fieldResolver){
		
		fieldResolvers.put(sourceFieldName.toLowerCase(), fieldResolver);
	}
	
	public FieldResolver<?, ?> getResolver(String fieldName){
		return fieldResolvers.get(fieldName.toLowerCase());
	}
}
