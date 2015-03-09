package simplemapper;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

class CollectionReflectionMapStrategy implements FieldMapStrategy{

	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public <R, T> R map(T from, R to, Method method) throws MapperException{
		to = null;
		
		Iterator<?> it = ((Collection<?>)to).iterator();
		Object first = it.next();
		try {
		if(from instanceof Collection){
			Object[] array = new Object[((Collection<?>) from).size()];
			int i = 0;
			for(Object o :(Collection<?>)  from){
				
				array[i++] = Mapper.map(o, it.next().getClass());
				((Collection<Object>)to).add(Mapper.map(o, first.getClass()));
			}
		}
		} catch (IllegalArgumentException e) {
			throw new MapperException(e);
		} catch (SecurityException e) {
			throw new MapperException(e);
		}
		return to;
	}

}
