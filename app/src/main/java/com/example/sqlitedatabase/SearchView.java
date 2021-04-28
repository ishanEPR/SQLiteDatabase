package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SearchView extends AppCompatActivity {
    androidx.appcompat.widget.SearchView searchView;
    ListView listView;

    String[] nameList={"Ishan","Reshmika","Dilshan","Sureka","Ananda","Ashini","Nelara"};

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchView=findViewById(R.id.search_bar);
       listView=findViewById(R.id.list_item);

       arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,nameList);
       listView.setAdapter(arrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getApplicationContext(),"You click "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
           }
       });

       searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               SearchView.this.arrayAdapter.getFilter().filter(query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               SearchView.this.arrayAdapter.getFilter().filter(newText);
               return false;
           }
       });





    }
}