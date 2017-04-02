package com.example.jay.formhw;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;
import android.util.Log;

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

        ArrayAdapter<String> adapterStation = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,station);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterTicket = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ticket);
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
            String checkMessage = "若需要email確認信，請勾選核選方塊";
            if (name.getText().toString().isEmpty()){
                new AlertDialog.Builder(MainActivity.this).setTitle("警告")
                        .setMessage("姓名為必填")
                        .setPositiveButton("朕知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();
            }
            else if (startStationSelect.equals(endStationSelect)){
                new AlertDialog.Builder(MainActivity.this).setTitle("警告")
                        .setMessage("您選擇的起迄站相同，請重新選擇")
                        .setPositiveButton("朕知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();
            }
            else if (Integer.parseInt(adTicketNum) + Integer.parseInt(chTicketNum) == 0){
                new AlertDialog.Builder(MainActivity.this).setTitle("警告")
                        .setMessage("您沒有訂任何票哦，請重新選擇")
                        .setPositiveButton("朕知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();
            }
            else if(!check && email.getText().toString().isEmpty()){
                new AlertDialog.Builder(MainActivity.this).setTitle("訂票資訊")
                        .setMessage("購票人: "+name.getText().toString()+'\n'
                                +"性別："+gender+'\n'
                                +"起站："+startStationSelect+'\n'
                                +"迄站："+endStationSelect+'\n'
                                +"全票："+adTicketNum+"張"+'\n'
                                +"兒童票："+chTicketNum+"張"+'\n'+'\n'
                                +checkMessage)
                        .setPositiveButton("朕知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();}
            else if(check && email.getText().toString().isEmpty()){
                new AlertDialog.Builder(MainActivity.this).setTitle("警告")
                        .setMessage("若要寄送確認信，請填寫您的信箱")
                        .setPositiveButton("朕知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();
            }
            else{
                Log.i("選擇發送工具", "");
                String[] TO = {email.getText().toString()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_SUBJECT, "訂票確認信");
                intent.putExtra(Intent.EXTRA_TEXT, "購票人: " + name.getText().toString() + '\n'
                        + "性別：" + gender + '\n'
                        + "起站：" + startStationSelect + '\n'
                        + "迄站：" + endStationSelect + '\n'
                        + "全票：" + adTicketNum + "張" + '\n'
                        + "兒童票：" + chTicketNum + "張");
                try {
                    startActivity(Intent.createChooser(intent, "選擇發送工具"));
                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
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
