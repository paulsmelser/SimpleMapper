package simplemapper.testsupport.entities;

import java.util.ArrayList;
import java.util.List;

public class FooList {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public static FooList  newInstance(String... items){
        FooList fooList = new FooList();
        fooList.list = new ArrayList<>();
        for (String item : items){
            fooList.list.add(item);
        }
        return fooList;
    }

    public FooList setList(List<String> list) {
        this.list = list;
        return this;
    }
}
