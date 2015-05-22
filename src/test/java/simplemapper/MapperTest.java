package simplemapper;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import entities.Bar;
import entities.BarComplexList;
import entities.BarList;
import entities.Foo;
import entities.FooComplexList;
import entities.FooList;
import entities.FooNumberFieldResolver;
import entities.ListFieldResolver;

@Category(simplemapper.UnitTests.class)
public class MapperTest extends TestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws MapperException {
		Mapper.createMap(Foo.class, Bar.class);
		Foo foo = new Foo("Hello", 12345);
		Bar bar = Mapper.map(foo, Bar.class);
		assertEquals(foo.getName(), bar.getName());
		assertTrue(bar.getNum() == 0);
	}
	@Test
	public void testMapWithList() throws MapperException {
		Mapper.createMap(FooList.class, BarList.class);
		FooList fooList = new FooList();
		fooList.setList(new ArrayList<String>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{ add("Hello"); add("World");}});
		BarList barList = Mapper.map(fooList, BarList.class);
		assertNotNull(barList);
	}
	@Test
	public void testMapWithComplexList() throws MapperException {
		Mapper.createMap(Foo.class, Bar.class).forField("number", new FooNumberFieldResolver());
		FooComplexList fooList = new FooComplexList();
		fooList.setList(new ArrayList<Foo>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{ add(new Foo("Hello", 12345));}});
		BarComplexList barList = Mapper.map(fooList, BarComplexList.class);
		assertTrue(barList.getList().get(0) instanceof Bar);
	}
	@Test
	public void testMapWithCustomMapping() throws MapperException{
		Mapper.createMap(FooComplexList.class, BarComplexList.class, new FooComplexMapping());
		FooComplexList fooList = new FooComplexList();
		fooList.setList(new ArrayList<Foo>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{ add(new Foo("Hello", 12345));}});
		BarComplexList barList = Mapper.map(fooList, BarComplexList.class);
		
		assertTrue(barList.getList().get(0) instanceof Bar);
		assertEquals(barList.getList().get(0).getName(), "Hello");
		assertEquals(barList.getList().get(0).getNum(), 12345);
	}
	@Test
	public void testMapWithCustomMappingForEachElement() throws MapperException{
		Mapper.createMap(FooComplexList.class, BarComplexList.class, new FooComplexMapping());
		Mapper.createMap(Foo.class, Bar.class, new FooMapping());
		
		FooComplexList fooList = new FooComplexList();
		fooList.setList(new ArrayList<Foo>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{ add(new Foo("Hello", 12345));}});
		BarComplexList barList = Mapper.map(fooList, BarComplexList.class);
		
		assertTrue(barList.getList().get(0) instanceof Bar);
		assertEquals(barList.getList().get(0).getName(), "Hello");
		assertEquals(barList.getList().get(0).getNum(), 12345);
	}
	@Test
	public void testMapWithFieldResolver() throws MapperException{
		Mapper.createMap(FooComplexList.class, BarComplexList.class).forField("list", new ListFieldResolver());
		Mapper.createMap(Foo.class, Bar.class, new FooMapping());
		
		FooComplexList fooList = new FooComplexList();
		fooList.setList(new ArrayList<Foo>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{ add(new Foo("Hello", 12345));}});
		BarComplexList barList = Mapper.map(fooList, BarComplexList.class);
		
		assertTrue(barList.getList().get(0) instanceof Bar);
		assertEquals(barList.getList().get(0).getName(), "Hello");
		assertEquals(barList.getList().get(0).getNum(), 12345);
	}
}
