package com.haurentziu.helios;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Vector;

/**
 * Created by haurentziu on 05.09.2016.
 */

class WebDirectoryDownloader extends AsyncTask<Void, Void, Vector<String>> {
    Context context;
    LinearLayout parent;
    String url, instrument, resolution;

    WebDirectoryDownloader(Context context, LinearLayout parent, String url, String instrument, String resolution){
        this.context = context;
        this.parent = parent;
        this.url = url;
        this.instrument = instrument;
        this.resolution = resolution;
    }

    @Override
    protected Vector<String> doInBackground(Void... params) {
        Vector<String> names = new Vector<>();
        try {
            Document doc = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .maxBodySize(0)
                    .timeout(600000)
                    .get();
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
        for(int i = 6; i < results.size(); i++){
            String name = results.get(i);
            String[] urlData = name.substring(0, name.length() - 4).split("_");
            if(urlData[2].equals(resolution) && urlData[3].equals(instrument)) {
                String hour = urlData[1].substring(0, 2);
                String minute = urlData[1].substring(2, 4);
                String second = urlData[1].substring(4, 6);

                ArchiveButton text = new ArchiveButton(context, String.format("%s:%s:%s", hour, minute, second), url + results.get(i));

                parent.addView(text);
                parent.addView(createVerticalSeparator());
            }
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
