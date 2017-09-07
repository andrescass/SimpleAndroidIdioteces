package com.example.nosotros.gridlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nosotros.gridlist.R;

import java.util.List;

public class MyListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> fruits;

    public MyListAdapter(Context context, int layout, List<String> fruits){
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // View Holder Pattern
        ViewHolder holder;

        if(convertView == null) {
            // Inflate the view
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            holder.fruitTextView = (TextView) convertView.findViewById(R.id.itemTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get current value (actual position)
        String currentFruit = fruits.get(position);

        // Fill the view's element
        holder.fruitTextView.setText(currentFruit);

        return convertView;
    }

    static class ViewHolder {
        private TextView fruitTextView;
    }
}
