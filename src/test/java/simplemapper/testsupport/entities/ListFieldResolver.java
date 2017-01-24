package simplemapper.testsupport.entities;

import java.util.ArrayList;

import simplemapper.FieldResolver;

public class ListFieldResolver implements FieldResolver<FooComplexList, BarComplexList>{

    @Override
    public void resolve(FooComplexList source, BarComplexList destination) {
        for(final Foo foo : source.getList()){
            Bar bar = new Bar(){{
                setName(foo.getName());
                setNum(foo.getNumber());
            }};
            if(destination.getList() == null){
                destination.setList(new ArrayList<Bar>());
            }
            destination.getList().add(bar);
        }
    }



}
