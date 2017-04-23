package spring.autowire.tests.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
@Component
@Scope("prototype")
public class PrototypeImplExtendsAbstractImplOfSomeInterface extends AbstractImplOfSomeInterface {

}
