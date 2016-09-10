package com.haurentziu.helios;

import android.os.Bundle;

import com.squareup.picasso.Picasso;
import uk.co.senab.photoview.PhotoView;

public class FullViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        createBarAndDrawer();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.darkBackground));

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");
        String name = extras.getString("name");
        String title = extras.getString("title");

        PhotoView touchImageView = (PhotoView) findViewById(R.id.view);
        Picasso.with(this).load(url).placeholder(getResources().getDrawable(R.drawable.loading_image)).into(touchImageView);

        super.changeTitleAndSubtitles(title, name);

    }

    @Override
    public void onBackPressed(){
        this.finish();
    }

}


