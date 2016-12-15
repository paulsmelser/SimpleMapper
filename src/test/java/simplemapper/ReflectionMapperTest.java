package simplemapper;

import static simplemapper.Mapper.createMap;

import org.junit.Test;

import simplemapper.testsupport.entities.Bar;
import simplemapper.testsupport.entities.Foo;
import simplemapper.testsupport.entities.FooComplexList;

public class ReflectionMapperTest {

    @Test
    public void test() throws MapperException {
        createMap(Foo.class, Bar.class).forField("number", (s, d) -> d.setNum(s.getNumber()));
        createMap(Bar.class, Foo.class).forField("num", (s, d) -> d.setNumber(s.getNum()));

        Bar psul = Mapper.map(new Foo().setName("Psul").setNumber(1), Bar.class);
        FooComplexList hello = FooComplexList.newInstance(new Foo("Hello", 12345));
    }

}