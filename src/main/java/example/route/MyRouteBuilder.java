package example.route;

import example.pojo.WeatherResponse;
import example.processor.WeatherProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final String CITY_TAG = "town";

        WeatherProcessor weatherProcessor = new WeatherProcessor();

        onException(Exception.class)
                .logHandled(false)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));

        from("direct:getCurrentWeather")
                .log(LoggingLevel.INFO, "example.route", "getCurrentWeather")
                .routeId("getCurrentWeather")
                .toD(generateServiceUrl())
                .unmarshal().json(JsonLibrary.Jackson, WeatherResponse.class)
                .process(weatherProcessor);
    }

    private static final String WEATHER_SERVICE_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private String apiKey = "093e27bcdd3869840bb030965ac4c373";

    private String generateServiceUrl() {
//        return String.format(WEATHER_SERVICE_URL, String.format("${property.%s}", CITY_TAG), apiKey);
        return String.format(WEATHER_SERVICE_URL, "London", apiKey);
    }
}