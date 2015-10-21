package info.developerblog.spring.thrift.api.gateway.filters;

import com.netflix.zuul.context.RequestContext;

/**
 * Created by aleksandr on 21.10.15.
 */
public interface ShouldAuthenticationFilterResolver {
    boolean resolve(RequestContext context);
}
