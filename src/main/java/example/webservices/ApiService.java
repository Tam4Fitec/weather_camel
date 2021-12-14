package example.webservices;

import example.pojo.WeatherResponse;
import javax.jws.WebService;


@WebService
public interface ApiService {

  WeatherResponse getCurrentWeather(String town);

}