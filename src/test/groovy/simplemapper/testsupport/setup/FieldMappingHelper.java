package simplemapper.testsupport.setup;

import simplemapper.FieldResolver;
import simplemapper.testsupport.entities.BarComplexList;
import simplemapper.testsupport.entities.FooComplexList;

/**
 * Created by psmelser on 2016-02-14.
 *
 * @author paul.smelser@gmail.com
 */
public class FieldMappingHelper {

    public static FieldResolver<FooComplexList, BarComplexList> getFooComplexListToBarComplexListNumberMapping(){
        return ((source, destination) -> {
            for(int i = 0; i != source.getList().size(); i++){
                destination.getList().get(i).setNum(source.getList().get(i).getNumber());
            }
        });
    }
}
