package simplemapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

class CollectionReflectionMapStrategy implements FieldMapStrategy {

    @SuppressWarnings({ "unchecked" })
    @Override
    public <R, T> R map(T from, R to, Method method) throws MapperException {
	try {
	    String property = method.getName().substring(3);
	    Field it = to.getClass().getDeclaredField(property.toLowerCase());
	    ParameterizedType stringListType = (ParameterizedType) it.getGenericType();
	    Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];

	    Method getMethod = to.getClass().getMethod(method.getName());

	    if (getMethod.invoke(to) == null) {
		to.getClass().getMethod("set" + property, getMethod.getReturnType())
			.invoke(to, new ArrayList<>());
	    }
	    if (from instanceof Collection) {
		for (Object o : (Collection<?>) from) {
		    ((Collection<Object>) getMethod.invoke(to)).add(Mapper.map(o, stringListClass));
		}
	    }
	} catch (IllegalArgumentException e) {
	    throw new MapperException(e);
	} catch (SecurityException e) {
	    throw new MapperException(e);
	} catch (NoSuchFieldException e) {
	    throw new MapperException(e);
	} catch (NoSuchMethodException e) {
	    throw new MapperException(e);
	} catch (IllegalAccessException e) {
	    throw new MapperException(e);
	} catch (InvocationTargetException e) {
	    throw new MapperException(e);
	}
	return to;
    }
}
