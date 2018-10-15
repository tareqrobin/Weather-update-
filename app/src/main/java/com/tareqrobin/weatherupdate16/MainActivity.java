package com.tareqrobin.weatherupdate16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tareqrobin.weatherupdate16.model.Model;
import com.tareqrobin.weatherupdate16.networking.ApiInterface;
import com.tareqrobin.weatherupdate16.networking.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView tvTimeZone, tvCurrentSummary, tvCurrentTemp,
             tvTomorrowSummary,tvTomorrowSunRise, tvTodaySunSet,
             tvTodayMaxTemp, tvMaxTempTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimeZone=findViewById(R.id.timezone);
        tvCurrentSummary=findViewById(R.id.current_summary);
        tvCurrentTemp=findViewById(R.id.current_temp);
        tvTomorrowSummary=findViewById(R.id.tomorrow_summary);
        tvTomorrowSunRise=findViewById(R.id.tomorrow_sunrise_time);
        tvTodaySunSet=findViewById(R.id.today_sunset_time);
        tvTodayMaxTemp=findViewById(R.id.today_max_temp);
        tvMaxTempTime=findViewById(R.id.time_today_max_temp);

        ApiInterface apiInterface=RetrofitApiClient.getRetrofit()
                                    .create(ApiInterface.class);
        Call<Model> call=apiInterface.getsubjectData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model data=response.body();
                String timezone=data.getTimezone();
                String currentSummary=data.getCurrently().getSummary();
                Double currentTemp= Double.valueOf(data.getCurrently().getTemperature());
                String tomorrowSummary=data.getDaily().getData().get(1).getSummary();
                String tomorrowSunRiseTime=data.getDaily().getData().get(1).getSunriseTime();
                String todaySunRiseTime=data.getDaily().getData().get(1).getSunriseTime();
                String todaySunSetTime=data.getDaily().getData().get(0).getSunsetTime();
                Double todayMaxTemp=data.getDaily().getData().get(0).getApparentTemperatureMax();
                String todayMaxTempTime=data.getDaily().getData().get(0).getTemperatureMaxTime();

                tvTimeZone.setText("Zone :"+timezone);
                tvCurrentSummary.setText("Summary :"+currentSummary);
                tvCurrentTemp.setText(currentTemp+" Degree celsius");
                tvTomorrowSummary.setText(tomorrowSummary);
                tvTomorrowSunRise.setText(tomorrowSunRiseTime);
                tvTodaySunSet.setText(todaySunSetTime);
                tvTodayMaxTemp.setText(todayMaxTemp+"Degree celsius");
                tvTodayMaxTemp.setText(todayMaxTempTime);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
