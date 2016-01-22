# Thrift API Gateway for Spring

[![Join the chat at https://gitter.im/aatarasoff/spring-thrift-api-gateway](https://badges.gitter.im/aatarasoff/spring-thrift-api-gateway.svg)](https://gitter.im/aatarasoff/spring-thrift-api-gateway?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Build Status](https://travis-ci.org/aatarasoff/spring-thrift-api-gateway.svg?branch=master)](https://travis-ci.org/aatarasoff/spring-thrift-api-gateway)

## What it is about

Gateway for Apache Thrift requests processing that is built on Spring Cloud stack. Project depends on core functionality that is described in https://github.com/aatarasoff/thrift-api-gateway-core
## How to connect the project

Its very simple:

```
repositories {
    jcenter()
}
```

```
compile 'info.developerblog.spring.thrift:spring-thrift-api-gateway:0.0.5'
```

## How to use this

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
