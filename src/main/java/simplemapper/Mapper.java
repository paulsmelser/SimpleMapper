package simplemapper;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Mapper
{
	private static HashMap<String, MapperConfiguration<?, ?>>  mappers;
	
	static
	{
		mappers = new HashMap<>();
	}
	public static <TS, TD> MapperConfiguration<TS, TD> createMap(Class<TS> source, Class<TD> destination)
	{
		MapperConfiguration<TS, TD> tc = new MapperConfiguration<>();
		
		mappers.put(createKey(source, destination), tc);
		return tc;
	}

	public static <TS, TD> MapperConfiguration<TS, TD> createMap(Class<TS> source, Class<TD> destination, CustomMapping<TS, TD> config)
	{
		MapperConfiguration<TS, TD> tc = new MapperConfiguration<>();
		tc.setCustomMapping(config);
		
		mappers.put(createKey(source, destination), tc);
		
		return tc;
	}

	@SuppressWarnings("unchecked")
	public static <TS, TD> TD map(TS source, Class<TD> destinationType) throws MapperException 
	{
		TD destination;
		try {
			destination = destinationType.newInstance();
			MapperConfiguration<TS, TD> mapperConfig = (MapperConfiguration<TS, TD>) mappers.get(createKey(source.getClass(), destinationType));

			for (Method method : source.getClass().getMethods()) {
				if (mapFromConfigIfExists(source, destination, mapperConfig, method)) continue;
				ReflectionMapper.mapOneField(source, destinationType, destination, method);
			}
			if (mapperConfig != null && mapperConfig.getCustomMapping() != null){
				mapperConfig.getCustomMapping().map(source, destination);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			throw new MapperException(e);
		}
		return destination;
	}

	private static <TS, TD> boolean mapFromConfigIfExists(TS source, TD destination, MapperConfiguration<TS, TD> mapperConfig, Method method) {
		if(mapperConfig != null){
        	FieldResolver<TS, TD> resolver = mapperConfig.getFieldResolver(getPropertyName(method));
            if(resolver != null){
                resolver.resolve(source, destination);
				return true;
            }
        }
		return false;
	}

    private static <TS, TD> String createKey(Class<TS> ts, Class<TD> td)
	{
		return String.format("%s%s", ts.getSimpleName(), td.getSimpleName());
	}

    private static String getPropertyName(Method method) {
        return method.getName().substring(3);
    }
}
