package spring.autowire.tests.singleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
@Component
@Scope("singleton")
public class SingletonImplExtendsAbstractImplOfSomeInterface extends AbstractImplOfSomeInterface {

}
