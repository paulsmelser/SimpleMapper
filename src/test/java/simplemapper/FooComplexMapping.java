package simplemapper;

import java.util.ArrayList;

import entities.Bar;
import entities.BarComplexList;
import entities.Foo;
import entities.FooComplexList;

public class FooComplexMapping implements CustomMapping<FooComplexList, BarComplexList>{

	@Override
	public void map(FooComplexList source, BarComplexList destination) throws MapperException {
		destination.setList(new ArrayList<Bar>());
		for ( final Foo foo : source.getList()){
			destination.getList().add(new Bar(){{
					setName(foo.getName());
					setNum(foo.getNumber());
				}});
		}
	}

}
