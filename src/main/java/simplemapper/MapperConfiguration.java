package simplemapper;

class MapperConfiguration<TS, TD>
{
	private CustomMapping<TS, TD> customMapping;

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
}
