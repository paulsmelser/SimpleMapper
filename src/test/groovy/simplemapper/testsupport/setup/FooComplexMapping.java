package simplemapper.testsupport.setup;

import simplemapper.CustomMapping;
import simplemapper.MapperException;
import simplemapper.testsupport.entities.Bar;
import simplemapper.testsupport.entities.BarComplexList;
import simplemapper.testsupport.entities.Foo;
import simplemapper.testsupport.entities.FooComplexList;

import java.util.ArrayList;

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
