package simplemapper;

public interface CustomMapping<TS, TD> {
	void map(TS source, TD destination) throws MapperException;
}
