package info.developerblog.examples.thirft.api;

import example.TGreetingExternalService;
import example.Token;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;

import static org.junit.Assert.assertEquals;

/**
 * Created by aleksandr on 01.09.15.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest*/
public class TGreetingServiceHandlerTests {

    @LocalServerPort
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
