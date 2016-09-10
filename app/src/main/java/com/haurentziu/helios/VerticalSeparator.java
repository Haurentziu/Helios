package com.haurentziu.helios;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by haurentziu on 05.09.2016.
 */
public class VerticalSeparator extends View {
    public VerticalSeparator(Context c){
        super(c);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1);
        int color = getResources().getColor(R.color.separatorColor);
        setLayoutParams(params);
        setBackgroundColor(color);

    }



}
