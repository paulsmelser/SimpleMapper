package simplemapper

import simplemapper.testsupport.entities.Bar
import simplemapper.testsupport.entities.BarComplexList
import simplemapper.testsupport.entities.BarList
import simplemapper.testsupport.entities.Foo
import simplemapper.testsupport.entities.FooComplexList
import simplemapper.testsupport.entities.FooList
import simplemapper.testsupport.entities.FooNumberFieldResolver
import simplemapper.testsupport.entities.ListFieldResolver
import simplemapper.testsupport.setup.FieldMappingHelper
import simplemapper.testsupport.setup.FooComplexMapping
import simplemapper.testsupport.setup.FooMapping
import spock.lang.Specification

import static groovy.util.GroovyTestCase.assertEquals
import static simplemapper.Mapper.createMap
import static simplemapper.Mapper.map
import static simplemapper.testsupport.Assert.assertAllTrue

/**
 * Created by psmelser on 2016-02-14.
 * @author paul.smelser@gmail.com
 */
class ReflectionMapperSpec extends Specification {

    def setupSpec(){
        createMap(FooList.class, BarList.class)
    }

    def "when a list is mapped all elements are mapped properly"(){
        given:
            def fooList = FooList.newInstance("Hello", "World")
        when:
            def barList = map(fooList, BarList.class)
        then:
            assertAllTrue(fooList.getList(), barList.getList(), checkThatListItemsAreAllEqual())
    }

    def "when a single item is mapping is correct"(){
        given:
            def foo = new Foo("Hello", 12345)
        when:
            def bar = map(new Foo("Hello", 12345), Bar.class)
        then:
            assertEquals(foo.getName(), bar.getName())
            assertEquals(0, bar.getNum())
    }

    def "test field resolvers in a complex list"(){
        given:
            createMap(Foo.class, Bar.class).forField("number", new FooNumberFieldResolver());
            def fooList = FooComplexList.newInstance(new Foo("Hello", 12345));
        when:
            def barList = map(fooList, BarComplexList.class);
        then:
            barList.getList().get(0) instanceof Bar
            assertAllTrue(fooList.getList(), barList.getList(), checkThatListItemsAreAllEqual())
    }



    def "test custom mapping configuration"(){
        given:
            createMap(FooComplexList.class, BarComplexList.class, new FooComplexMapping())
            def fooList = FooComplexList.newInstance(new Foo("Hello", 12345))
        when:
            def barList = map(fooList, BarComplexList.class)
        then:
            barList.getList().get(0) instanceof Bar
        assertEquals(barList.getList().get(0).getName(), fooList.getList().get(0).getName())
        assertEquals(barList.getList().get(0).getNum(), fooList.getList().get(0).getNumber())
    }

    def "test custom mapping for elements in a list"(){
        given:
            createMap(FooComplexList.class, BarComplexList.class, new FooComplexMapping());
            createMap(Foo.class, Bar.class, new FooMapping());
            def fooList = FooComplexList.newInstance(new Foo("Hello", 12345));
        when:
            def barList = map(fooList, BarComplexList.class);
        then:
            assertAllTrue(fooList.getList(), barList.getList(), checkThatListItemsAreAllEqual())
    }

    def "test mapping list with field resolvers for list items"(){
        given:
            createMap(FooComplexList.class, BarComplexList.class).forField("list", new ListFieldResolver())
            createMap(Foo.class, Bar.class, new FooMapping())
            def fooList = FooComplexList.newInstance(new Foo("Hello", 12345))
        when:
            def barList = map(fooList, BarComplexList.class);
        then:
            assertAllTrue(fooList.getList(), barList.getList(), checkThatListItemsAreAllEqual())
    }

    def "test field mapping handler"(){
        given:
            createMap(FooComplexList.class, BarComplexList.class).forField ("number", FieldMappingHelper.getFooComplexListToBarComplexListNumberMapping())
            createMap(Foo.class, Bar.class, new FooMapping())
            def fooList = FooComplexList.newInstance(new Foo("Hello", 12345))
        when:
            def barList = map(fooList, BarComplexList.class)
        then:
            assertAllTrue(fooList.getList(), barList.getList(), checkThatListItemsAreAllEqual()
        )
    }

    private static Closure<Boolean> checkThatListItemsAreAllEqual() {
        { left, right ->
            (left.getNumber().equals(right.getNum())
                    && left.getName().equals(right.getName()))
        }
    }
}
