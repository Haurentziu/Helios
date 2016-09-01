package com.haurentziu.helios;

import android.os.Bundle;
import android.support.v4.widget.Space;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createBarAndDrawer();
        createGUI();

    }

    private void createGUI(){
        LinearLayout vertLayout = (LinearLayout) findViewById(R.id.verticalLayout);
        LinearLayout currentHorizontal = createHorizontalLayout();
        String urls[] = getResources().getStringArray(R.array.sunLinks);
        String names[] = getResources().getStringArray(R.array.names);
        String fullSizes[] = getResources().getStringArray(R.array.full_screen_resolutions);
        int n = 0;

        for(int i = 0; i < urls.length; i++){
            if(n == 2){
                currentHorizontal = createHorizontalLayout();
                vertLayout.addView(currentHorizontal);
                n = 0;
            }

            SunThumbnail thumb = new SunThumbnail(this, urls[i], names[i], fullSizes[i]);

            currentHorizontal.addView(thumb);

            if(n != 1){
                currentHorizontal.addView(createSpace(0.01f));
            }

            n++;
        }

        currentHorizontal.addView(createSpace(0.5f));

    }


    private Space createSpace(float weight){
        Space space = new Space(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = weight;
        space.setLayoutParams(params);
        return space;

    }


    private LinearLayout createHorizontalLayout(){
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 10;
        horizontalLayout.setLayoutParams(params);
        return horizontalLayout;

    }

   /* @Override
    public void onBackPressed(){
        this.finish();
    }*/


}
