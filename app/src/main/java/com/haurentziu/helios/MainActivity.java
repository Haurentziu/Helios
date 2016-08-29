package com.haurentziu.helios;

import android.os.Bundle;
import android.support.v4.widget.Space;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;


public class MainActivity extends BaseActivity {

    private final String[] urls = {
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0193.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0304.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0171.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0211.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0131.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0335.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_0094.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_1600.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_1700.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/f_211_193_171_%d.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/f_304_211_171_%d.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/f_094_335_193_%d.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/f_HMImag_171_%d.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMIB.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMIBC.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMIIC.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMIIF.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMII.jpg",
            "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_%d_HMID.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/eit_171/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/eit_195/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/eit_284/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/eit_304/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/hmi_igr/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/c2/%d/latest.jpg",
            "http://sohowww.nascom.nasa.gov/data/realtime/c3/%d/latest.jpg",
    };

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
       // vertLayout.addView(currentHorizontal);
        int n = 0;

        for(String url : urls){
            if(n == 3){
                currentHorizontal = createHorizontalLayout();
                vertLayout.addView(currentHorizontal);
                n = 0;
            }

            SunThumbnail thumb = new SunThumbnail(this, url);

            currentHorizontal.addView(thumb);

            if(n != 2){
                currentHorizontal.addView(createSpace(0.005f));
            }

            n++;
        }

        currentHorizontal.addView(createSpace(0.33f));
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


}
