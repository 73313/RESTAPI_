package utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;



@UtilityClass
public class RetrofitUtils {
    //public String baseUrl="http://80.78.248.82:8189/market/api/v1/";
  //  HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    static Properties properties = new Properties();
    private static InputStream configFile;

   static {
       try {configFile = new FileInputStream("src/test/resources/MyProperties.properties");
       }
       catch (FileNotFoundException e){e.printStackTrace();}
   }
   @SneakyThrows
   public String getBaseUrl(){
       properties.load(configFile);
       return properties.getProperty("url");

   }

  public Retrofit getRetrofit() {
      return new Retrofit.Builder()
              // .baseUrl(baseUrl)
              .baseUrl(getBaseUrl())
              .addConverterFactory(JacksonConverterFactory.create())
              .build();
  }


 /* public Retrofit getRetrofit(){

      logging.setLevel(BODY);

      httpClient.addInterceptor(logging);
      return new Retrofit.Builder()
              .baseUrl(getBaseUrl())
              .addConverterFactory(JacksonConverterFactory.create())

              .client(httpClient.build())
              .build();
  }*/




}
