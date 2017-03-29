package com.example.jay.formhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spnStart;
    private Spinner spnEnd;
    private Spinner spnAd;
    private Spinner spnCh;
    String[] station = new String[] {"南港","台北","板橋","桃園","新竹","台中","嘉義","台南","左營"};
    String[] ticket = new String[] {"0","1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnStart = (Spinner) findViewById(R.id.spinner);
        spnEnd = (Spinner) findViewById(R.id.spinner2);
        spnAd = (Spinner) findViewById(R.id.spinner3);
        spnCh = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> adapterStation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,station);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterTicket = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ticket);
        adapterTicket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnStart.setAdapter(adapterStation);
        spnEnd.setAdapter(adapterStation);
        spnAd.setAdapter(adapterTicket);
        spnCh.setAdapter(adapterTicket);

    }
}
