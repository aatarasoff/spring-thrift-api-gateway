package info.developerblog.spring.thrift.api.gateway;

import com.netflix.zuul.context.RequestContext;
import info.developerblog.spring.thrift.api.gateway.filters.AuthenticationZuulFilter;
import info.developerblog.spring.thrift.api.gateway.filters.ShouldAuthenticationFilterResolver;
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

    @Bean
    @ConditionalOnMissingBean(ShouldAuthenticationFilterResolver.class)
    public ShouldAuthenticationFilterResolver shouldAuthenticationFilterResolver() {
        return new ShouldAuthenticationFilterResolver() {
            @Override
            public boolean resolve(RequestContext context) {
                String contentType = context.getRequest().getHeader("Content-Type");

                return null != contentType
                        && "application/x-thrift".equals(contentType);
            }
        };
    }
}
