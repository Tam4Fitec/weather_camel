package example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class WeatherProcessorTest implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getOut().setBody("2021-12-14 : 12.54 Celsius", String.class);
    }

}