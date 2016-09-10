package com.haurentziu.helios;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by haurentziu on 07.09.2016.
 */

public class ArchiveBrowser extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_browser);
        super.createBarAndDrawer();

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");
        String instrument = extras.getString("instrument");
        String resolution = extras.getString("resolution");
        LinearLayout parent = (LinearLayout) findViewById(R.id.browserLayout);

        super.changeTitleAndSubtitles(getResources().getString(R.string.archive_explore),
                getResources().getString(R.string.set_time));

        new WebDirectoryDownloader(this, parent, url, instrument, resolution).execute();
    }
}
