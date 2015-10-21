package info.developerblog.spring.thrift.api.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.aatarasoff.thrift.api.gateway.core.AuthTokenExchanger;
import ru.aatarasoff.thrift.api.gateway.core.MessageTransalator;

import java.io.ByteArrayInputStream;

/**
 * Created by aleksandr on 11.09.15.
 */
@Slf4j
public class AuthenticationZuulFilter extends ZuulFilter {
    @Autowired
    AuthTokenExchanger authTokenExchanger;

    @Autowired
    TProtocolFactory protocolFactory;

    @Autowired
    ShouldAuthenticationFilterResolver shouldAuthenticationFilterResolver;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return shouldAuthenticationFilterResolver.resolve(RequestContext.getCurrentContext());
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequestWrapper request = (HttpServletRequestWrapper) ctx.getRequest();

        MessageTransalator messageTransalator = new MessageTransalator(protocolFactory, authTokenExchanger);

        try {
            byte[] processed = messageTransalator.process(request.getContentData());
            ctx.set("requestEntity", new ByteArrayInputStream(processed));
            ctx.setOriginContentLength(processed.length);
        } catch (TException e) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseDataStream(new ByteArrayInputStream(new byte[]{}));

            try {
                ctx.getResponse().getOutputStream().write(messageTransalator.processError(e));
            } catch (Exception e1) {
                log.error("unexpected error", e1);
                ctx.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseDataStream(new ByteArrayInputStream(new byte[]{}));

            log.error("unexpected error", e);
            ctx.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        return null;
    }
}
