package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab7.adapter.AdapterName;
import com.example.lab7.data.DataName;
import com.example.lab7.model.Name;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Name> list;
    AdapterName adapter;
    TextView txt;
    Button btnAdd,btnRe, btnCan;
    DataName data;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new DataName(this);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnRe = findViewById(R.id.btnRemove);
        btnCan = findViewById(R.id.btnCancel);
        txt = findViewById(R.id.txtInput);

        list = new ArrayList<>();
        list = data.getAllName();

        adapter = new AdapterName(this,R.layout.item_name,list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                Toast.makeText(MainActivity.this, index + "", Toast.LENGTH_SHORT).show();

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt.getText().toString();
                if(!name.equalsIgnoreCase("")){
                    txt.setText("");
                    data.addName(new Name(name));
                    update();
                }
            }
        });

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteName(list.get(index));
                update();
            }
        });

    }

    private void update() {

        list.clear();
        List<Name> temp = data.getAllName();
        for (Name n : temp){
            list.add(n);
        }
        adapter.notifyDataSetChanged();
    }
}