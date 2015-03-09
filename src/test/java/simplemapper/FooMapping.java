package simplemapper;

import entities.Bar;
import entities.Foo;

public class FooMapping implements CustomMapping<Foo, Bar>{

	@Override
	public void map(Foo source, Bar destination) throws MapperException {
		destination.setName(source.getName());
		destination.setNum(source.getNumber());
	}

}
