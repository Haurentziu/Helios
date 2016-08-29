package com.haurentziu.helios;

import android.os.Bundle;

import com.ortiz.touch.TouchImageView;
import com.squareup.picasso.Picasso;

public class FullViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        createBarAndDrawer();

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");

        TouchImageView touchImageView = (TouchImageView) findViewById(R.id.touchView);
        touchImageView.setMaxZoom(999999);
        Picasso.with(this).load(url).into(touchImageView);
    }

}
