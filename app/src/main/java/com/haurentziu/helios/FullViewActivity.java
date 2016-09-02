package com.haurentziu.helios;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;
import uk.co.senab.photoview.PhotoView;

public class FullViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        createBarAndDrawer();

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");
        String name = extras.getString("name");

        PhotoView touchImageView = (PhotoView) findViewById(R.id.view);
        Picasso.with(this).load(url).into(touchImageView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle(name);

    }

    @Override
    public void onBackPressed(){
        this.finish();
    }

}


