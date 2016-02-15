package simplemapper;

/**
 * Created by psmelser on 2016-02-14.
 *
 * @author paul.smelser@gmail.com
 */
@FunctionalInterface
public interface FieldMapping<S, D>{
    void map(S source, D destination);
}
