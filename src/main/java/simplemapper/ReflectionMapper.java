package simplemapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class ReflectionMapper {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
	
	public static <TTo> TTo map(Object from, Class<TTo> to) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, NoSuchFieldException{
		TTo result = to.newInstance();

			for(Method method : from.getClass().getMethods()){
				try{
					if (method.getName().startsWith("get") && check(to, method) && !isBaseMethod(method)){
						String setterName = "set" + method.getName().substring(3);
						result.getClass().getMethod(setterName, method.getReturnType()).invoke(result, method.invoke(from));
					}
				}
				catch(Exception e){
					String setterName = "set" + method.getName().substring(3);
					result.getClass().getMethod(setterName, method.getReturnType()).invoke(result, Mapper.map(method.invoke(from), result.getClass()));
				}
			}

		return result;
	}
	
	public static boolean check(Class<?> to, Method from){
		Method[] methods = to.getMethods();
		for (Method m : methods){
			if (m.getName().equals(from.getName())){
				if (m.getReturnType().equals(from.getReturnType())){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isWrapperType(Class<?> cls)
    {
        return WRAPPER_TYPES.contains(cls);
    }
	private static boolean isBaseMethod(Method from){
		return from.getName().equals("getClass");
	}
	
	
	
	private static Set<Class<?>> getWrapperTypes()
    {
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
