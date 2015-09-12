package info.developerblog.examples.thirft.api;

import info.developerblog.spring.thrift.api.gateway.annotation.EnableThriftGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by aleksandr on 01.09.15.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableThriftGateway
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
