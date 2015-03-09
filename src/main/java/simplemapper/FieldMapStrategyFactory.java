package simplemapper;

import java.util.Collection;
import java.util.Map;

class FieldMapStrategyFactory {

	public static FieldMapStrategy create(Object initialObject){
		if(initialObject instanceof Collection){
			return new CollectionReflectionMapStrategy();
		}
		else if (initialObject instanceof Map){
			return new SimpleReflectionMapStrategy();
		}
		return new SimpleReflectionMapStrategy();
	}
}
