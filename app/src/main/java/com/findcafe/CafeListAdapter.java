package com.findcafe;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tianying on 4/04/2016.
 */
public class CafeListAdapter extends BaseAdapter {

    private Context context;
    private List<Cafe> cafeList;
    private LayoutInflater layoutInflater;
    TextView cafeNameView;
    TextView cafeDistanceView;
    TextView cafeAddressView;

    public CafeListAdapter(Context context, List<Cafe> cafeList)
    {
        this.context = context;
        this.cafeList = cafeList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public void update(List<Cafe> cafeList) {
        this.cafeList = cafeList;
    }

    @Override
    public int getCount() {
        return cafeList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_cafe_list, null);
        }

        Cafe cafe = cafeList.get(position);

        // Set values
        cafeNameView = (TextView)convertView.findViewById(R.id.cafe_name_view);
        cafeDistanceView = (TextView)convertView.findViewById(R.id.cafe_distance_view);
        cafeAddressView = (TextView)convertView.findViewById(R.id.cafe_address_view);
        cafeNameView.setText(cafe.getCafeName());
        cafeDistanceView.setText(String.valueOf(cafe.getCafeDistance()));
        cafeAddressView.setText(cafe.getCafeAddress());

        // Bold cafe name text
        TextPaint tp = cafeNameView.getPaint();
        tp.setFakeBoldText(true);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
