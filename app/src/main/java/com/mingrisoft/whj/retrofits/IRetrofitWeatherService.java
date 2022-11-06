package com.mingrisoft.whj.retrofits;

import com.mingrisoft.whj.retrofits.bean.CityWeatherBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitWeatherService {
    @GET("wether/")
    Call<CityWeatherBean> getWeatherInfo(@Query("city") String city);
}
