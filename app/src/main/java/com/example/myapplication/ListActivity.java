package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    ListView listView;


    TextView textView;
    ProgressBar progressBar;
    ArrayAdapter adapter;
    ImageView imageView;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        progressBar = findViewById(R.id.progressBar2);
        listView = findViewById(R.id.listView);




        List<String> titles = new ArrayList();
        List<String> imageUrls = new ArrayList();
        List<Article> articles = new ArrayList<Article>();

        try {


            System.out.print("doing in back ");
            System.out.print("starting ");
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.
                    create()).build();

            NewsApi newsApi = retrofit.create(NewsApi.class);

            Call<News> call = newsApi.getAllNews();

            call.enqueue(
                    new Callback<News>() {

                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            Log.d("ds", " start");
                            if (!response.isSuccessful()) {
                                Log.d("d", "not successful");

                                return;
                            } else {
                                Log.d("dg", " success");

                                News news = response.body();


                                for (Article article : news.getArticles()) {
                                    articles.add(article);
                                    titles.add(article.getTitle());
                                    imageUrls.add(article.getUrlToImage());
                                }
                                progressBar.setVisibility(View.INVISIBLE);

                                if (titles.size() > 0)
                                    adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.itemlayout, R.id.textView1, titles);
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        /*webView.loadUrl(articles.get(i).getUrl());
                                        setContentView(R.layout.webview);*/
                                        Log.d("dsda", articles.get(i).getUrl());

                                        Intent intent =new Intent(getApplicationContext(),WebviewActivity.class);
                                        intent.putExtra("url", articles.get(i).getUrl());

                                        startActivity(intent);
                                    }

                                });
                                {
                                }

                                /*    */
                            }
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    }
            );


        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
//textView=findViewById(R.id.textView);

    }


    public static Bitmap getBitmapFromURL(String src) {

        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;

        }
    }

}