package simplemapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;

class CollectionReflectionMapStrategy implements FieldMapStrategy{

	@SuppressWarnings({ "unchecked" })
	@Override
	public <R, T> R map(T from, R to, Method method) throws MapperException{
		try {
		Field it = to.getClass().getDeclaredField(method.getName().substring(3).toLowerCase());
		ParameterizedType stringListType = (ParameterizedType) it.getGenericType();
		Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];

		if(from instanceof Collection){
			for(Object o :(Collection<?>)  from){
				((Collection<Object>)to).add(Mapper.map(o, stringListClass));
			}
		}
		} catch (IllegalArgumentException e) {
			throw new MapperException(e);
		} catch (SecurityException e) {
			throw new MapperException(e);
		} catch (NoSuchFieldException e) {
			throw new MapperException(e);
		}
		return to;
	}

}
