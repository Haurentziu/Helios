package com.haurentziu.helios;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by haurentziu on 02.09.2016.
 */
public class ArchiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        createBarAndDrawer();
        LinearLayout parent = (LinearLayout) findViewById(R.id.archive_layout);

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");
        new WebDirectoryDisplayer(this, parent, url).execute();
    }


}

class WebDirectoryDisplayer extends AsyncTask<Void, Void, Vector<String>> {
    Context context;
    LinearLayout parent;
    String url;

    WebDirectoryDisplayer(Context context, LinearLayout parent, String url){
        this.context = context;
        this.parent = parent;
        this.url = url;
    }

    @Override
    protected Vector<String> doInBackground(Void... params) {
        Vector<String> names = new Vector<>();
        try {
            Document doc = Jsoup.connect(url).timeout(10000).get();
            doc.select("img").remove();
            Elements links = doc.getElementsByTag("a");
            for(Element link : links){
                String name = link.text();
                names.add(name);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return names;
    }


    @Override
    protected void onPostExecute(Vector<String> results) {
        for(int  i = 5; i < results.size(); i++){
            ArchiveButton text = new ArchiveButton(context, results.get(i).replace("/", "  "), url + results.get(i));

            parent.addView(text);
            parent.addView(createVerticalSeparator());
        }
    }

    View createVerticalSeparator(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1);
        int color = context.getResources().getColor(R.color.separatorColor);
        View view = new View(context);
        view.setLayoutParams(params);
        view.setBackgroundColor(color);
        return view;
    }
}
