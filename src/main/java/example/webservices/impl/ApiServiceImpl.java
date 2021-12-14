package example.webservices.impl;

import example.pojo.WeatherResponse;
import example.webservices.ApiService;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@WebService
public class ApiServiceImpl implements ApiService {
    public static final String SEARCH_BY_CITY_NAME_PARAM = "/SEARCH_BY_CITY_NAME/";
    public static final String SEARCH_BY_CITY_ID_PARAM = "/SEARCH_BY_CITY_ID/";
    public static final String SEARCH_BY_ZIP_CODE_PARAM = "/SEARCH_BY_ZIP_CODE/";
    public static final String SEARCH_BY_COORDINATE_PARAM = "/SEARCH_BY_COORDINATE/";

    @GET
    @Path("/getWeather/town/{town}")
    public WeatherResponse getCurrentWeather(
        @PathParam("town") String town) {

        return null;
    }

    @GET
    @Path("/getWeather" + SEARCH_BY_CITY_NAME_PARAM + "{attribute}")
    public WeatherResponse getWeatherSearchByCityName(@PathParam("attribute") String attribute) { return null; }

    @GET
    @Path("/getWeather" + SEARCH_BY_CITY_ID_PARAM + "{attribute}")
    public WeatherResponse getWeatherSearchByCityId(@PathParam("attribute") String attribute) { return null; }

    @GET
    @Path("/getWeather" + SEARCH_BY_ZIP_CODE_PARAM + "{attribute}")
    public WeatherResponse getWeatherSearchByZipCode(@PathParam("attribute") String attribute) { return null; }

    @GET
    @Path("/getWeather" + SEARCH_BY_COORDINATE_PARAM + "{attribute}")
    public WeatherResponse getWeatherSearchByCoordinate(@PathParam("attribute") String attribute) { return null; }
}