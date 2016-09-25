package simplemapper;

@FunctionalInterface
public interface FieldResolver <TS, TD>{
	void resolve(TS source, TD destination);
}
