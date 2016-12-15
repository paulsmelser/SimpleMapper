package simplemapper.testsupport.entities;

public class Foo{
    private String name;
    private int number;
    public Foo(){}
    public Foo(String name, int number) {
        super();
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public Foo setName(String name) {
        this.name = name;
        return this;
    }
    public int getNumber() {
        return number;
    }
    public Foo setNumber(int number) {
        this.number = number;
        return this;
    }
}
