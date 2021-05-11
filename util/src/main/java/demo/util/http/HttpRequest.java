package demo.util.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {

  public static Retrofit buildRequest(String url) {

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build();
    return retrofit;

  }


}
