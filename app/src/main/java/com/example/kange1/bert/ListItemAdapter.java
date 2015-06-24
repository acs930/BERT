package com.example.kange1.bert;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemAdapter extends ArrayAdapter<ListItem> {

    private LayoutInflater mInflater;

    public ListItemAdapter(Context context, int rid, List<ListItem> list){
        super(context, rid, list);
        mInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup partent){

        ListItem item = (ListItem)getItem(position);

        View view = mInflater.inflate(R.layout.list_item, null);

        ImageView image;
        image = (ImageView)view.findViewById(R.id.image);
        image.setImageBitmap(item.image);

        TextView name;
        name = (TextView)view.findViewById(R.id.name);
        name.setText(item.name);

        return view;
    }
}
