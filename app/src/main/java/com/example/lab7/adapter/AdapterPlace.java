package com.example.lab7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab7.MainActivityPlace;
import com.example.lab7.R;
import com.example.lab7.data.DataPlace;
import com.example.lab7.model.Place;

import java.util.List;

public class AdapterPlace extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Place> list;
    private DataPlace data;

    public AdapterPlace(Context context, int idLayout, List<Place> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout,parent,false);

        }
        Place place = list.get(position);

        TextView txtId = convertView.findViewById(R.id.textViewPlaceId);
        EditText txtName = convertView.findViewById(R.id.textViewPlaceName);
        ImageButton btnU = convertView.findViewById(R.id.btnItemUpdate);
        ImageButton btnD = convertView.findViewById(R.id.btnItemDelete);

        txtId.setText(place.getId()+".");
        txtName.setText(place.getName());


        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place.setName(txtName.getText().toString());
                MainActivityPlace instance = MainActivityPlace.getInstance();
                instance.getDb().updatePlace(place);
                Toast.makeText(instance, "", Toast.LENGTH_SHORT).show();
                instance.update();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityPlace instance = MainActivityPlace.getInstance();
                instance.getDb().deletePlace(place.getId());
                Toast.makeText(instance, "", Toast.LENGTH_SHORT).show();
                instance.update();
            }
        });

        return convertView;
    }
}
