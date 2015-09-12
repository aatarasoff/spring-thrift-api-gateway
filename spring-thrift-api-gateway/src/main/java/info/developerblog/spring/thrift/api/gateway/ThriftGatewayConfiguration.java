package info.developerblog.spring.thrift.api.gateway;

import info.developerblog.spring.thrift.api.gateway.filters.AuthenticationZuulFilter;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aatarasoff.thrift.api.gateway.core.AuthTokenExchanger;

/**
 * Created by aleksandr on 12.09.15.
 */
@Configuration
public class ThriftGatewayConfiguration {
    @Bean
    @ConditionalOnMissingBean(AuthTokenExchanger.class)
    AuthTokenExchanger authTokenExchanger() {
        throw new UnsupportedOperationException("You should implement AuthTokenExchanger bean");
    }

    @Bean
    @ConditionalOnMissingBean(TProtocolFactory.class)
    TProtocolFactory thriftProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    @Bean
    public AuthenticationZuulFilter authenticationZuulFilter() {
        return new AuthenticationZuulFilter();
    }
}
