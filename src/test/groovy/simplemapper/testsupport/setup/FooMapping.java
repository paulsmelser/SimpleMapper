package simplemapper.testsupport.setup;

import simplemapper.CustomMapping;
import simplemapper.MapperException;
import simplemapper.testsupport.entities.Bar;
import simplemapper.testsupport.entities.Foo;

public class FooMapping implements CustomMapping<Foo, Bar>{

    @Override
    public void map(Foo source, Bar destination) throws MapperException {
        destination.setName(source.getName());
        destination.setNum(source.getNumber());
    }

}
