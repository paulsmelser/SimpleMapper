package simplemapper;

public interface FieldResolver <TS, TD>{
	public void resolve(TS object, TD destination);
}
