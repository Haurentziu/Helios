package com.haurentziu.helios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by haurentziu on 02.09.2016.
 */

public class ArchiveButton extends TextView implements OnTouchListener{
    private String targetLink;
    private boolean isClicked = false;
    private boolean longClicked = false;
    private long lastDown;
    private final static int SLEEP_TIME = 120;
    private final int SELECTED_COLOR = getResources().getColor(R.color.selectedColor);
    private final int DEFAULT_COLOR = getResources().getColor(R.color.lightterBackground);

    public ArchiveButton(Context c, String name, String targetLink){
        super(c);
        this.targetLink = targetLink;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        params.leftMargin = 16;
        setText(name);
        setTextSize(20);
        setGravity(Gravity.CENTER_VERTICAL);
        setLayoutParams(params);

        setOnTouchListener(this);

    }


    private void doOnClick(){
        setBackgroundColor(SELECTED_COLOR);

        if(targetLink.substring(targetLink.length() - 4).equals(".jpg")) {
            Intent intent = new Intent(getContext(), FullViewActivity.class);
            String splitLink[] = targetLink.split("/");
            intent.putExtra("url", targetLink);
            intent.putExtra("name", splitLink[splitLink.length - 1]);
            intent.putExtra("title", getResources().getString(R.string.archive_explore));
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
        if(event.getAction() == MotionEvent.ACTION_CANCEL){
            setBackgroundColor(DEFAULT_COLOR);
            isClicked = false;
            longClicked = false;
        }

        else if(event.getAction() == MotionEvent.ACTION_UP ){
            longClicked = false;


            if(isClicked) {
                setBackgroundColor(SELECTED_COLOR);
                doOnClick();
            }
            setBackgroundColor(DEFAULT_COLOR);

        }

        else if(event.getAction() == MotionEvent.ACTION_DOWN){
            isClicked = true;

            if(!longClicked){
                longClicked = true;
                lastDown = System.currentTimeMillis();
            }

        }

        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            int deltaT = (int) (System.currentTimeMillis() - lastDown);

            if(deltaT > 50) {
                setBackgroundColor(SELECTED_COLOR);
                longClicked = false;
            }
            else{
                setBackgroundColor(DEFAULT_COLOR);
            }

        }

        return true;
    }


}
