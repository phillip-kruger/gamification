package com.github.phillipkruger.graphql;

import com.github.phillipkruger.model.Weather;
import com.github.phillipkruger.service.WeatherService;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class WeatherGraphQLApi {
    
    @Inject
    WeatherService weatherService;
    
//    @Query
//    public Weather getWeather(String city){
//        return weatherService.getWeather(city);
//    }
    
    @Query
    public CompletionStage<Weather> getWeather(String city){
        return weatherService.getFutureWeather(city);
    }
}