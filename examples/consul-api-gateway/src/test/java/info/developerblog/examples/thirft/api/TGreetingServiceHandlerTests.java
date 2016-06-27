package info.developerblog.examples.thirft.api;

import example.TGreetingExternalService;
import example.TName;
import example.Token;
import example.UnauthorizedException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by aleksandr on 01.09.15.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest*/
public class TGreetingServiceHandlerTests {

    @Value("${local.server.port}")
    int port;

    @Autowired
    TProtocolFactory protocolFactory;

    TGreetingExternalService.Iface client;

    //@Before
    public void setUp() throws Exception {
        TTransport transport = new THttpClient("http://localhost:" + port + "/greetings/api");

        TProtocol protocol = protocolFactory.getProtocol(transport);

        client = new TGreetingExternalService.Client(protocol);
    }

    //@Test
    public void testSimpleCall() throws Exception {
        assertEquals("Hello John Smith", client.greet(new Token("heisours")));
    }

    //@Test(expected = UnauthorizedException.class)
    public void testUnauthorizedCall() throws Exception {
        client.greet(new Token("heisnot"));
    }
}
