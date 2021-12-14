package example.processor;

import example.pojo.WeatherResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeatherProcessor implements Processor {
    private Logger logger = LoggerFactory.getLogger(WeatherProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("In process");
        final WeatherResponse weatherResponse = exchange.getIn().getBody(WeatherResponse.class);

        try {
            Long timestampLong = weatherResponse.getDt();
            Date date = new Date(timestampLong * 1000);
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

            double dayTemp = weatherResponse.getMain().getTemp();

            exchange.getOut().setBody(dateString + ": " + dayTemp + " Celsius", String.class);
        } catch (Exception e) {
            exchange.getOut().setBody("KO", String.class);
            logger.debug("[WeatherProcessor] error" + e.getMessage());
        }
    }

}
