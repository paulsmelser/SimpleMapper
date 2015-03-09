package simplemapper;

import java.util.HashMap;

public class Mapper
{
	private static HashMap<String, MapperConfiguration<?, ?>>  mappers;
	static
	{
		mappers = new HashMap<String, MapperConfiguration<?, ?>>();
	}
	public static <TS, TD> MapperConfiguration<TS, TD> createMap(Class<TS> source, Class<TD> destination)
	{
		MapperConfiguration<TS, TD> tc = new MapperConfiguration<TS, TD>();
		
		mappers.put(createKey(source, destination), tc);
		return tc;
	}

	public static <TS, TD> MapperConfiguration<TS, TD> createMap(Class<TS> source, Class<TD> destination, CustomMapping<TS, TD> config)
	{
		MapperConfiguration<TS, TD> tc = new MapperConfiguration<TS, TD>();
		tc.setCustomMapping(config);
		
		mappers.put(createKey(source, destination), tc);
		
		return tc;
	}
	
	public static <TS, TD> TD map(TS source, Class<TD> destinationType) throws MapperException 
	{
		TD destination = ReflectionMapper.map(source, destinationType);

		@SuppressWarnings("unchecked")
		MapperConfiguration<TS, TD> mapperConfig = (MapperConfiguration<TS, TD>) mappers.get(createKey(source.getClass(), destinationType));
		if (mapperConfig != null && mapperConfig.getCustomMapping() != null) mapperConfig.getCustomMapping().map(source, destination);
		return destination;
	}

	private static <TS, TD> String createKey(Class<TS> ts, Class<TD> td)
	{
		return String.format("%s%s", ts.getSimpleName(), td.getSimpleName());
	}
}
