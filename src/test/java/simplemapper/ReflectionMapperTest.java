package simplemapper;

import static simplemapper.Mapper.createMap;

import org.junit.Test;

import simplemapper.testsupport.entities.Bar;
import simplemapper.testsupport.entities.Foo;
import simplemapper.testsupport.entities.FooComplexList;

public class ReflectionMapperTest {

    @Test
    public void test(){
        createMap(Foo.class, Bar.class).forField("number", (s, d) -> {
            d.setNum(s.getNumber());
        });
        FooComplexList hello = FooComplexList.newInstance(new Foo("Hello", 12345));
    }

}