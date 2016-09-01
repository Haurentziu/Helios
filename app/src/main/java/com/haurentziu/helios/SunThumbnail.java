package com.haurentziu.helios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View.OnClickListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by haurentziu on 29.08.2016.
 */
public class SunThumbnail extends RelativeLayout implements OnClickListener{
    String url;
    String name;
    String fullSize;

    public SunThumbnail(Context c, String url, String name, String fullSize){
        super(c);
        this.url = url;
        this.name = name;
        this.fullSize = fullSize;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.weight = .5f;

        setLayoutParams(params);
        Drawable defaultDrawable = getResources().getDrawable(R.drawable.loading_image);


        RelativeLayout.LayoutParams imageParams  = new RelativeLayout.LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        ImageView image = new ImageView(c);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setLayoutParams(params);
        image.setClickable(true);
        image.setLayoutParams(imageParams);
        image.setOnClickListener(this);
        image.setImageDrawable(defaultDrawable);


        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams
                (LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        textParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

        TextView text = new TextView(c);
        text.setText(name);
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        text.setLayoutParams(textParams);
        text.setTextSize(13f);
        int color = getResources().getColor(R.color.text_background);
        text.setBackgroundColor(color);

        addView(image);
        addView(text);
        Picasso.with(getContext()).load(String.format(url, "512")).placeholder(defaultDrawable).into(image);
    }



    @Override
    public void onClick(View view){
        Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra("url", String.format(url, fullSize));
        intent.putExtra("name", name);
        getContext().startActivity(intent);
    }
}
