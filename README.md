# Thrift API Gateway for Spring

## What is it

Gateway for Apache Thrift requests processing that is built on Spring Cloud stack

## How connect project

Its very simple:

```
repositories {
    maven {
        url  "http://dl.bintray.com/aatarasoff/maven"
    }
}
```

```
compile 'info.developerblog.spring.thrift:spring-thrift-api-gateway-core:0.0.3'
```

## How use this

Add annotations @EnableThriftClient and @EnableZuulProxy to your spring boot main application class

```
@SpringBootApplication
@EnableZuulProxy
@EnableThriftGateway
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Next you need to create AuthTokenExchanger implementation and register it as a bean

```
@Bean
    AuthTokenExchanger authTokenExchanger() {
        return new AuthTokenExchanger<Token, User>() {
            @Override
            public Token createEmptyAuthToken() {
                return new Token();
            }

            @Override
            public User process(Token authToken) throws TException {
                // you token exchange logic
            }
        };
    }
```

Last, you need configure Zuul with static routes or with dynamic cloud

## Enjoy!
