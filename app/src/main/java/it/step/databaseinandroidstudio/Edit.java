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

public class Edit extends AppCompatActivity {

    private EditText EditTextOne;
    private EditText EditTextTwo;
    private EditText EditTextThree;
    private EditText EditTextFour;
    public Button ButtonOne;
    public Button ButtonTwo;
    public Button ButtonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        EditTextOne = findViewById(R.id.EditTextOne);
        EditTextTwo = findViewById(R.id.EditTextTwo);
        EditTextThree = findViewById(R.id.EditTextThree);
        EditTextFour = findViewById(R.id.EditTextFour);

        ButtonOne = findViewById(R.id.ButtonOne);
        ButtonTwo = findViewById(R.id.ButtonTwo);
        ButtonThree = findViewById(R.id.ButtonThree);


        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("deviceName").toString();
        String t3 = i.getStringExtra("model").toString();
        String t4 = i.getStringExtra("problems").toString();

        EditTextFour.setText(t1);
        EditTextOne.setText(t2);
        EditTextTwo.setText(t3);
        EditTextThree.setText(t4);


        ButtonTwo.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Delete();
                Intent intent = new Intent(getApplicationContext(), ViewDB.class);
                startActivity(intent);
            }
        });


        ButtonThree.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent intent = new Intent(getApplicationContext(), ViewDB.class);
                startActivity(intent);

            }
        });




        ButtonOne.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                EditButton();
            }
        });

    }

    public void Delete()

    {
        try
        {
            String id = EditTextFour.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Запись удалена",Toast.LENGTH_LONG).show();


            EditTextOne.setText("");
            EditTextTwo.setText("");
            EditTextThree.setText("");
            EditTextOne.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Запись не выполнена!",Toast.LENGTH_LONG).show();
        }
    }
    public void EditButton()
    {
        try
        {
            String deviceName = EditTextOne.getText().toString();
            String model = EditTextTwo.getText().toString();
            String problems = EditTextThree.getText().toString();
            String id = EditTextFour.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "update records set deviceName = ?,model=?,problems=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,deviceName);
            statement.bindString(2,model);
            statement.bindString(3,problems);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Запись обновлена",Toast.LENGTH_LONG).show();


            EditTextOne.setText(null);
            EditTextTwo.setText(null);
            EditTextThree.setText(null);
            EditTextOne.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Запись не выполнена",Toast.LENGTH_LONG).show();
        }

    }

}