package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editname,editsurname,editmarks;
    Button BtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DatabaseHelper(this);

        editname=findViewById(R.id.name);
        editsurname=findViewById(R.id.surname);
        editmarks=findViewById(R.id.marks);

        BtnAdd=findViewById(R.id.add);
        AddData();

    }
    public void  AddData()
    {
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean inserted= mydb.insetData(editname.getText().toString(),editsurname.getText().toString(),editmarks.getText().toString());

               if (inserted==true){
                   Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG);
               }else{
                   Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT);
               }
            }
        });
    }
}