package entities;

import simplemapper.FieldResolver;

public class FooNumberFieldResolver implements FieldResolver<Foo, Bar>{

	@Override
	public void resolve(Foo source, Bar destination) {
		destination.setNum(source.getNumber());
	}

}
