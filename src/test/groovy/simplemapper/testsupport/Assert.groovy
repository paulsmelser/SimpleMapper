package simplemapper.testsupport

import static junit.framework.TestCase.assertTrue

/**
 * Created by psmelser on 2016-02-14.
 * @author paul.smelser@gmail.com
 */
class Assert {
    static def <E, A> void assertAllTrue(List<E> expectedList, List<A> actualList, Closure<Boolean> closure){
        for (int i = 0; i != expectedList.size(); i++){
            def actual = actualList.get(i)
            def expected = expectedList.get(i)
                assertTrue(closure(expected, actual))
        }
    }

}
