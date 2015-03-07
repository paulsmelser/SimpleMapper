package simplemapper;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import simplemapper.Mapper;
import entities.Bar;
import entities.Foo;

public class MapperTests {

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
	public void test() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		Mapper.createMap(Foo.class, Bar.class);
		Foo foo = new Foo("Hello", 12345);
		Bar bar = Mapper.map(foo, Bar.class);
		assertEquals(foo.getName(), bar.getName());
		assertTrue(bar.getNum() == 0);
	}
	
	


}
