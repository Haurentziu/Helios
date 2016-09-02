package com.haurentziu.helios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by haurentziu on 02.09.2016.
 */
public class ArchiveButton extends TextView implements OnTouchListener, OnClickListener{
    private String targetLink;
    private boolean isScrolling = false;

    public ArchiveButton(Context c, String name, String targetLink){
        super(c);
        this.targetLink = targetLink;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);

        setText(name);
        setTextSize(20);
        setGravity(Gravity.CENTER_VERTICAL);
        setLayoutParams(params);

        this.setOnTouchListener(this);
        this.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        isScrolling = false;
        if(targetLink.substring(targetLink.length() - 4).equals(".jpg")) {
            Intent intent = new Intent(getContext(), FullViewActivity.class);
            intent.putExtra("url", targetLink);
            intent.putExtra("name", targetLink);
            getContext().startActivity(intent);
        }
        else {
            Intent intent = new Intent(getContext(), ArchiveActivity.class);
            intent.putExtra("url", targetLink);
            getContext().startActivity(intent);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

       if(event.getAction() == MotionEvent.ACTION_UP){
            int color = getResources().getColor(R.color.darkBackground);
            setBackgroundColor(color);
            isScrolling = false;
        }

        else if(event.getAction() == MotionEvent.ACTION_DOWN ){
            int color = getResources().getColor(R.color.selectedColor);
            setBackgroundColor(color);

        }


        return false;
    }
}
