package simplemapper;

import java.lang.reflect.Method;

interface FieldMapStrategy {

	public <R, T> R map(T from, R to, Method method) throws MapperException;
}
