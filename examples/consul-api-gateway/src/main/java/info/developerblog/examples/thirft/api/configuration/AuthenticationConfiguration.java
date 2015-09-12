package info.developerblog.examples.thirft.api.configuration;

import example.ErrorCode;
import example.TName;
import example.Token;
import example.UnauthorizedException;
import org.apache.thrift.TException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aatarasoff.thrift.api.gateway.core.AuthTokenExchanger;

/**
 * Created by aleksandr on 12.09.15.
 */
@Configuration
public class AuthenticationConfiguration {
    @Bean
    AuthTokenExchanger authTokenExchanger() {
        return new AuthTokenExchanger<Token, TName>() {
            @Override
            public Token createEmptyAuthToken() {
                return new Token();
            }

            @Override
            public TName process(Token authToken) throws TException {
                if (authToken.getValue().equals("heisours")) {
                    return new TName("John", "Smith");
                }

                throw new UnauthorizedException(ErrorCode.WRONG_LOGIN_OR_PASSWORD);
            }
        };
    }
}
