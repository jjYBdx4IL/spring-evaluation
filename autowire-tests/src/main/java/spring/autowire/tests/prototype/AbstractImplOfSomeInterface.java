package spring.autowire.tests.prototype;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
public abstract class AbstractImplOfSomeInterface implements SomeInterface {

    private String value = null;

    @Override
    public String getHelloString() {
        return "Hello";
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
