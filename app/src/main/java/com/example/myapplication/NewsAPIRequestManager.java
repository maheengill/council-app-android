package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.NewsModels.NewsAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsAPIRequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsArticles(OnFetchDataListener listener, String category, String query){
        CallNewsAPI callNewsAPI = retrofit.create(CallNewsAPI.class);
        Call<NewsAPIResponse> call = callNewsAPI.callAPI("Dewsbury OR Cleckheaton", "title,description","2022-04-17", "skysports.com,ansa.it", "en", context.getString(R.string.api_key));

        try{
            call.enqueue(new Callback<NewsAPIResponse>() {
                @Override
                public void onResponse(Call<NewsAPIResponse> call, Response<NewsAPIResponse> response) {
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsAPIResponse> call, Throwable t) {
                    Toast.makeText(context, "Response not successful", Toast.LENGTH_LONG);
                    listener.onError("Request not successful");

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public NewsAPIRequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsAPI{
        @GET("everything")
        Call<NewsAPIResponse> callAPI(
                @Query("q") String q,
                @Query("searchIn") String searchIn,
                @Query("from") String from,
                @Query("excludeDomains") String excludeDomains,
                @Query("language") String language,
                @Query ("apiKey") String apiKey
        );
    }
}
