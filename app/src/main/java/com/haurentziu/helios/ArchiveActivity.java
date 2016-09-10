package com.haurentziu.helios;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by haurentziu on 02.09.2016.
 */
public class ArchiveActivity extends BaseActivity implements View.OnClickListener {
    private ArchiveDatePicker pickerDialog;
    protected String[] instrumentURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        super.createBarAndDrawer();

        Bundle extras = getIntent().getExtras();
        findViewById(R.id.dateButton).setOnClickListener(this);
        findViewById(R.id.archiveGet).setOnClickListener(this);

        pickerDialog = new ArchiveDatePicker();
        pickerDialog.setDateButton((Button) findViewById(R.id.dateButton));
        pickerDialog.updateButtonDate();

        instrumentURLs = getResources().getStringArray(R.array.instrument_links);
        setSpinnerValues(R.id.instrumentSpinner, R.array.instrument_names);
        setSpinnerValues(R.id.resolutionSpinner, R.array.resolutions);

        super.changeTitle(getResources().getString(R.string.archive_explore));

    }

    private void setSpinnerValues(int spinnerId, int valuesId){
        Spinner spinner = (Spinner) findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                valuesId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Button datePicker = (Button) findViewById(R.id.dateButton);
        Button archiveGet = (Button) findViewById(R.id.archiveGet);
        if(v == datePicker){
            pickerDialog.show(getFragmentManager(), "datePicker");
        }

        else if (v == archiveGet){
            Intent intent = new Intent(this, ArchiveBrowser.class);

            Spinner instrumentSpinner = (Spinner) findViewById(R.id.instrumentSpinner);
            int pos = instrumentSpinner.getSelectedItemPosition();

            Spinner resolutionsSpinner = (Spinner) findViewById(R.id.resolutionSpinner);
            String resolution = (String) resolutionsSpinner.getSelectedItem();

            int year = pickerDialog.getYear();
            int month = pickerDialog.getMonth();
            int dayOfMonth = pickerDialog.getDayOfMonth();

            String url = String.format("http://sdo.gsfc.nasa.gov/assets/img/browse/%d/%02d/%02d/", year, month + 1, dayOfMonth);

            intent.putExtra("url", url);
            intent.putExtra("instrument", instrumentURLs[pos]);
            intent.putExtra("resolution", resolution);
            startActivity(intent);
        }
    }




}

