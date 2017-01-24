package simplemapper;

import static com.google.common.collect.Maps.newHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

class MapperConfiguration<TS, TD>
{
	private CustomMapping<TS, TD> customMapping;
	private Map<String, FieldResolver<TS, TD>> fieldResolvers = newHashMap();
//    private Map<String, BiConsumer<TS, TD>> lambdaFieldResolvers = newHashMap();

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

	
	public FieldResolver<TS, TD> getFieldResolver(String fieldName){
		return fieldResolvers.get(fieldName.toLowerCase());
	}

//    public BiConsumer<TS, TD> getLambdaFieldResolver(String fieldName) {
//        return lambdaFieldResolvers.get(fieldName);
//    }
}
