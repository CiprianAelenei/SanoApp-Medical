package com.ase.sanoapp.advice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.ase.sanoapp.R;
import com.ase.sanoapp.controller.HomeActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class ParseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    private Toolbar toolbar;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);


        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerViewParse);
        backBtn = findViewById(R.id.back_btn_edmed);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItems, this);
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }


    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(ParseActivity.this, android.R.anim.fade_in));
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(ParseActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://www.sfatulmedicului.ro/articole-medicale";
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("li.clearfix");
                int size = data.size();
                Log.d("doc", "doc: " + doc);
                Log.d("data", "data: " + data);
                Log.d("size", "" + size);
                for (int i = 0; i < size; i++) {
                    String imgUrl = data.select("li.clearfix")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("li.clearfix")
                            .select("img")
                            .eq(i)
                            .attr("title");

                    String hrefUrl = data.select("li.clearfix")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    parseItems.add(new ParseItem(imgUrl, title, hrefUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title);

                }

                Elements data2 = doc.select("ul.right");
                String newUrl = "https:" + data2.select("ul.right").select("a").attr("href");

                Document doc2 = Jsoup.connect(newUrl).get();

                Elements data3 = doc2.select("li.clearfix");
                int size3 = data3.size();
                Log.d("doc", "doc: " + doc2);
                Log.d("data", "data: " + data3);
                Log.d("size", "" + size3);
                for (int i = 0; i < size3; i++) {
                    String imgUrl = data3.select("li.clearfix")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data3.select("li.clearfix")
                            .select("img")
                            .eq(i)
                            .attr("title");

                    String hrefUrl = data3.select("li.clearfix")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    parseItems.add(new ParseItem(imgUrl, title, hrefUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
