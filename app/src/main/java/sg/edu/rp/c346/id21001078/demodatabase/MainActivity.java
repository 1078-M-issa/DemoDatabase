package sg.edu.rp.c346.id21001078.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btnInsert, buttonRetrive;
    TextView TV;
    ListView lv;
    EditText ET1, ET2;
    RadioGroup SB;
    RadioButton RB1, RB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        buttonRetrive = findViewById(R.id.button2);
        TV = findViewById(R.id.textView);
        lv = findViewById(R.id.lv);
        ET1 = findViewById(R.id.ET1);
        ET2 = findViewById(R.id.ET2);
        SB = findViewById(R.id.SBRG);
        RB1 = findViewById(R.id.radioButton);
        RB2 = findViewById(R.id.radioButton2);




        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(ET1.getText().toString(), ET2.getText().toString());

            }
        });

        buttonRetrive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                DBHelper db2 = new DBHelper(MainActivity.this);

                if (RB1.isChecked()) {
                    ArrayList<String> data = db.getTaskContent();
                    db.close();
                    ArrayList<Task> al = db2.getTasks();
                    db2.close();

                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);

                    String txt = "";
                    for (int i = 0; i < data.size(); i++) {
                        Log.d("Database Content", i +". "+data.get(i));
                        txt += i + ". " + data.get(i) + "\n";
                    }
                    TV.setText(txt);
                    lv.setAdapter(aa);

                } else if (RB2.isChecked()) {
                    ArrayList<String> data = db.getTaskContent2();
                    db.close();
                    ArrayList<Task> al = db2.getTasks2();
                    db2.close();

                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);

                    String txt = "";
                    for (int i = 0; i < data.size(); i++) {
                        Log.d("Database Content", i +". "+data.get(i));
                        txt += i + ". " + data.get(i) + "\n";
                    }
                    TV.setText(txt);
                    lv.setAdapter(aa);
                }




            }
        });
    }



}
