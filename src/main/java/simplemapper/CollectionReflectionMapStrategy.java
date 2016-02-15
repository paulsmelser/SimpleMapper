package simplemapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

class CollectionReflectionMapStrategy implements FieldMapStrategy {

    @SuppressWarnings({ "unchecked" })
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
				if (o.getClass().equals(stringListClass) || o.getClass().isAssignableFrom(stringListClass)){
					((Collection<Object>) getMethod.invoke(to)).add(o);
				}
				else {
					((Collection<Object>) getMethod.invoke(to)).add(Mapper.map(o, stringListClass));
				}
			}
	    }
	} catch (IllegalArgumentException | SecurityException | NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
	    throw new MapperException(e);
	}
		return to;
    }
}
