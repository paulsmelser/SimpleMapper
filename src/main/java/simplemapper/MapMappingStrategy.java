package simplemapper;

import java.lang.reflect.Method;

public class MapMappingStrategy implements FieldMapStrategy{

	@Override
	public <R, T> R map(T from, R to, Method method) throws MapperException {
		return to = null;
	}

}
