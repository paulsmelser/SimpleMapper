package simplemapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SimpleReflectionMapStrategy implements FieldMapStrategy{

	@Override
	public <R, T> R map(T from, R to, Method method) throws MapperException{
		String setterName = "set" + method.getName().substring(3);
		try {
			to.getClass().getMethod(setterName, method.getReturnType()).invoke(to, from);
		} catch (IllegalAccessException e) {
			throw new MapperException(e);
		} catch (IllegalArgumentException e) {
			throw new MapperException(e);
		} catch (InvocationTargetException e) {
			throw new MapperException(e);
		} catch (NoSuchMethodException e) {
			throw new MapperException(e);
		} catch (SecurityException e) {
			throw new MapperException(e);
		}
		return to;
	}

}
