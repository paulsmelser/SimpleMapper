package simplemapper.testsupport

import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


/**
 * Created by psmelser on 2016-02-14.
 * @author paul.smelser@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE, ElementType.METHOD])
@Inherited
public @interface Slow {
}
