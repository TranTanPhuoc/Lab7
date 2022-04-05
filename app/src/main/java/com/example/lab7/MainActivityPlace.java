package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab7.adapter.AdapterPlace;
import com.example.lab7.data.DataPlace;
import com.example.lab7.model.Place;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPlace extends AppCompatActivity {
    ListView listView;
    List<Place> list;
    AdapterPlace adapter;
    TextView txt;
    Button btnSave,btnCan;

    int index = -1;

    private static MainActivityPlace instance;
    private static DataPlace data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_place);

        instance = this;

        data  = new DataPlace(this);
        listView = findViewById(R.id.listViewPlace);
        txt = findViewById(R.id.edtInputPlace);
        btnSave = findViewById(R.id.btnSave);

        list = new ArrayList<>();
        list = data.getAllPlace();
        adapter = new AdapterPlace(this,R.layout.item_place,list);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(txt.getText().toString().equalsIgnoreCase(""))){
                    Place place = new Place(txt.getText().toString());
                    data.addPlace(place);
                    txt.setText("");
                    update();
                }
            }
        });
    }

    public static MainActivityPlace getInstance(){
        return instance;
    }

    public static DataPlace getDb() {
        return data;
    }

    public void update() {
        list.clear();
        List<Place> temp = data.getAllPlace();
        for (Place n : temp){
            list.add(n);
        }
        adapter.notifyDataSetChanged();
    }

}