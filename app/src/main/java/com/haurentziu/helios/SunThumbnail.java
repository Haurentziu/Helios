package com.haurentziu.helios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by haurentziu on 29.08.2016.
 */
public class SunThumbnail extends ImageView implements OnClickListener{
    String url;

    public SunThumbnail(Context c, String url){
        super(c);
        this.url = url;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.weight = .33f;
        setAdjustViewBounds(true);
        setScaleType(ImageView.ScaleType.FIT_CENTER);
        setLayoutParams(params);
        setClickable(true);
        setOnClickListener(this);
        Picasso.with(getContext()).load(String.format(url, 512)).into(this);
    }



    @Override
    public void onClick(View view){
        Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra("url", String.format(url, 1024));
        getContext().startActivity(intent);
    }
}
