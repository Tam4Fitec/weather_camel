package example.processor;

import example.pojo.WeatherResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeatherProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        final WeatherResponse weatherResponse = exchange.getIn().getBody(WeatherResponse.class);

        Long timestampLong = weatherResponse.getDt();
        Date date = new Date(timestampLong * 1000);
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        double dayTemp = weatherResponse.getMain().getTemp();

        exchange.getOut().setBody(dateString + ": " + dayTemp + " Celsius", String.class);
    }

}
