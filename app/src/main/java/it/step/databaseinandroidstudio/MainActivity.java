package it.step.databaseinandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText EditTextOne;
    private EditText EditTextTwo;
    private EditText EditTextThree;
    public Button ButtonOne;
    public Button ButtonTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditTextOne = findViewById(R.id.EditTextOne);
        EditTextTwo = findViewById(R.id.EditTextTwo);
        EditTextThree = findViewById(R.id.EditTextThree);

        ButtonOne = findViewById(R.id.ButtonOne);
        ButtonTwo = findViewById(R.id.ButtonTwo);

        ButtonTwo.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(getApplicationContext(), ViewDB.class);
                startActivity(i);
            }
        });
        ButtonOne.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                insert();
            }
        });
    }

    public void insert() {
        try {
            String deviceName = EditTextOne.getText().toString();
            String model = EditTextTwo.getText().toString().toUpperCase(Locale.ROOT);
            String problems = EditTextThree.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,deviceName VARCHAR,model VARCHAR,problems VARCHAR)");

            String sql = "insert into records(deviceName,model,problems)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            if (!deviceName.equals("")) {
                if (!model.equals("")) {
                    if (!problems.equals("")) {
                        statement.bindString(1, deviceName);
                        statement.bindString(2, model);
                        statement.bindString(3, problems);
                        statement.execute();
                    }
                    }
                }
            else {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_LONG).show();
            }


            Toast.makeText(this, "Записоно в базу", Toast.LENGTH_LONG).show();

            EditTextOne.setText("");
            EditTextTwo.setText("");
            EditTextThree.setText("");
            EditTextOne.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Запись не выполнена", Toast.LENGTH_LONG).show();
        }
    }
}
