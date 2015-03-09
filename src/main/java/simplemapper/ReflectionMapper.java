package simplemapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class ReflectionMapper {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static <TTo> TTo map(Object from, Class<TTo> to)
			throws MapperException {
		TTo result = null;
		try {
			result = to.newInstance();
			for (Method method : from.getClass().getMethods()) {
			result = mapOneField(from, to, result, method);
			}
		} catch (InstantiationException e) {
			throw new MapperException(e);
		} catch (IllegalAccessException e) {
			throw new MapperException(e);
		} catch (IllegalArgumentException e) {
			throw new MapperException(e);
		} catch (SecurityException e) {
			throw new MapperException(e);
		}

		return result;
	}

	static <TTo> TTo mapOneField(Object from, Class<TTo> to,
			TTo result, Method method) throws MapperException {
		if (shouldMap(to, method)) {

			Object initialObject;

			try {
				initialObject = method.invoke(from);
			} catch (IllegalAccessException e) {
				throw new MapperException(e);
			} catch (IllegalArgumentException e) {
				throw new MapperException(e);
			} catch (InvocationTargetException e) {
				throw new MapperException(e);
			}
			FieldMapStrategy mapStrategy = FieldMapStrategyFactory.create(initialObject);
			result = mapStrategy.map(initialObject, result, method);
		}
		return result;
	}

	private static <TTo> boolean shouldMap(Class<TTo> to, Method method) {
		return method.getName().startsWith("get")
				&& isMatchingMethod(to, method) && !isBaseMethod(method);
	}

	public static boolean isMatchingMethod(Class<?> to, Method from) {
		Method[] methods = to.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(from.getName())) {
				if (m.getReturnType().equals(from.getReturnType())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isWrapperType(Class<?> cls) {
		return WRAPPER_TYPES.contains(cls);
	}

	private static boolean isBaseMethod(Method from) {
		return from.getName().equals("getClass");
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		return ret;
	}

}
