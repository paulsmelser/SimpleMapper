package simplemapper.testsupport.entities;

import java.util.ArrayList;
import java.util.List;

public class FooComplexList {
    private List<Foo> list;

    public List<Foo> getList() {
        return list;
    }

    public static FooComplexList newInstance(Foo... items){
        FooComplexList fooComplexList = new FooComplexList();
        fooComplexList.list = new ArrayList<>();
        for (Foo item : items){
            fooComplexList.list.add(item);
        }
        return fooComplexList;
    }
    public void setList(List<Foo> list) {
        this.list = list;
    }
}
