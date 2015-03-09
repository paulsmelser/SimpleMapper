package entities;

import simplemapper.FieldResolver;

public class ListFieldResolver implements FieldResolver<FooComplexList, BarComplexList>{

	@Override
	public void resolve(FooComplexList source, BarComplexList destination) {
		for(final Foo foo : source.getList()){
			Bar bar = new Bar(){{
				setName(foo.getName());
				setNum(foo.getNumber());
			}};
			destination.getList().add(bar);
		}
	}



}
