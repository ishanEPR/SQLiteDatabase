package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editname,editsurname,editmarks,Editid;
    Button BtnAdd,BtnView,BtnUpdate,BtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DatabaseHelper(this);

        editname=findViewById(R.id.name);
        editsurname=findViewById(R.id.surname);
        editmarks=findViewById(R.id.marks);
        Editid=findViewById(R.id.id);

        BtnAdd=findViewById(R.id.add);
        BtnView=findViewById(R.id.view);
        BtnUpdate=findViewById(R.id.update);
        BtnDelete=findViewById(R.id.delete);

        AddData();
        ViewAll();

        updateData();
        deleteData();
    }
    public void ViewAll()
    {
        BtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res=mydb.getAllData();
               if (res.getCount()==0)
               {
                   showMessage("Error","No Data Found");
                    return;
               }
               StringBuffer buffer=new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("ID :"+res.getString(0)+"\n");
                   buffer.append("NAME :"+res.getString(1)+"\n");
                   buffer.append("SURNAME :"+res.getString(2)+"\n");
                   buffer.append("MARKS :"+res.getString(3)+"\n\n");
               }

               //show all data
                showMessage("Data",buffer.toString());
            }
        });
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

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updateData(){
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=mydb.updateData(Editid.getText().toString(),editname.getText().toString(),editsurname.getText().toString(),editmarks.getText().toString());
                if (isUpdated==true){
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG);
                }
                else{
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void deleteData()
    {
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows=mydb.deleteData(Editid.getText().toString());
                if (deletedRows>0)
                {
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_LONG);
                }
                else{
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_LONG);
                }
            }
        });
    }

}