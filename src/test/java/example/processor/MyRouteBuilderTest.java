package example.processor;

import example.route.MyRouteBuilder;
import org.apache.camel.EndpointInject;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class MyRouteBuilderTest  extends CamelTestSupport {

    @EndpointInject(uri = "mock:http://api.openweathermap.org/data/2.5/weather?q=London&appid=093e27bcdd3869840bb030965ac4c373&units=metric")
    MockEndpoint mockWeather;

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MyRouteBuilder();
    }

    @Test
    public void getCurrentWeatherMockTest() throws Exception {
        File file = new File("src/test/resources");
        String absolutePath = file.getAbsolutePath();

        context.addRoutes(new MyRouteBuilder() {
            @Override public void configure() throws Exception {
                from("direct:getCurrentWeather").id("getCurrentWeather")
                        .process(new WeatherProcessorTest())
                        .to("mock:output");
            }
        });

        MockEndpoint mock = getMockEndpoint("mock:output");
        String expected = "2021-12-14 : 12.54 Celsius";
        mockWeather.expectedMessageCount(0);
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived(expected);

        String input = FileUtils.readFileToString(new File(absolutePath+"/tempe.json"), StandardCharsets.UTF_8);
        template.sendBody("direct:getCurrentWeather",input );
        assertMockEndpointsSatisfied();
    }
}