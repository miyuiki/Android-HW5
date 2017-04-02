package com.example.jay.formhw;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private RadioGroup gen;
    private RadioButton man , women;
    private Spinner spnStart;
    private Spinner spnEnd;
    private Spinner spnAd;
    private Spinner spnCh;
    private CheckBox emailBox;
    private Button yes,no;

    String[] station = new String[] {"南港","台北","板橋","桃園","新竹","台中","嘉義","台南","左營"};
    String[] ticket = new String[] {"0","1","2","3","4","5"};
    String gender, startStationSelect, endStationSelect;
    String adTicketNum, chTicketNum;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);
        gen = (RadioGroup) findViewById(R.id.genGroup);
        man = (RadioButton) findViewById(R.id.radioButton);
        women = (RadioButton) findViewById(R.id.radioButton2);
        spnStart = (Spinner) findViewById(R.id.spinner);
        spnEnd = (Spinner) findViewById(R.id.spinner2);
        spnAd = (Spinner) findViewById(R.id.spinner3);
        spnCh = (Spinner) findViewById(R.id.spinner4);
        emailBox = (CheckBox) findViewById(R.id.checkBox);
        yes = (Button) findViewById(R.id.button);
        no = (Button) findViewById(R.id.button2);

        ArrayAdapter<String> adapterStation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,station);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterTicket = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ticket);
        adapterTicket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gen.setOnCheckedChangeListener(genSelectListener);
        spnStart.setOnItemSelectedListener(startStationListener);
        spnEnd.setOnItemSelectedListener(endStationListener);
        spnAd.setOnItemSelectedListener(adTicketListener);
        spnCh.setOnItemSelectedListener(chTicketListener);
        emailBox.setOnCheckedChangeListener(emailCheckListener);
        yes.setOnClickListener(okListerner);
        no.setOnClickListener(cancelListener);

        spnStart.setAdapter(adapterStation);
        spnEnd.setAdapter(adapterStation);
        spnAd.setAdapter(adapterTicket);
        spnCh.setAdapter(adapterTicket);

    }
    private RadioGroup.OnCheckedChangeListener genSelectListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId == R.id.radioButton){
                gender = man.getText().toString();
            }
            else if(checkedId == R.id.radioButton2){
                gender = women.getText().toString();
            }
        }
    };
    private Spinner.OnItemSelectedListener startStationListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            startStationSelect = parent.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };
    private Spinner.OnItemSelectedListener endStationListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            endStationSelect = parent.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };
    private Spinner.OnItemSelectedListener adTicketListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            adTicketNum = parent.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };
    private Spinner.OnItemSelectedListener chTicketListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            chTicketNum = parent.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };
    private CheckBox.OnCheckedChangeListener emailCheckListener = new CheckBox.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            check = emailBox.isChecked();
        }
    };
    private Button.OnClickListener okListerner = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(MainActivity.this).setTitle("訂票資訊")
                    .setMessage("購票人: "+name.toString())
                    .setMessage("性別： "+gender)
                    .show();
        }
    };
    private Button.OnClickListener cancelListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("確認頁面")
                    .setMessage("您真的要取消訂票並結束程式嗎？")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
        }
    };
}
