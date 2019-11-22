package codes.umair.findthatbook;

import android.app.Application;

import com.google.gson.GsonBuilder;

import codes.umair.findthatbook.api.BooksAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private BooksAPI api;
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/";

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(client)
                .build();

        api = retrofit.create(BooksAPI.class);
    }
    public BooksAPI getApi(){
        return api;
    }
}
