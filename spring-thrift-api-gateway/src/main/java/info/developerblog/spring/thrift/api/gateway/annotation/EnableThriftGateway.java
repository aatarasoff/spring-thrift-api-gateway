package info.developerblog.spring.thrift.api.gateway.annotation;

import info.developerblog.spring.thrift.api.gateway.ThriftGatewayConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by aleksandr on 12.09.15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ThriftGatewayConfiguration.class)
public @interface EnableThriftGateway {
}
