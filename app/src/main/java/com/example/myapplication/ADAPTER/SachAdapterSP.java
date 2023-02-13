package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DTO.Sach;
import com.example.myapplication.R;

import java.util.List;

public class SachAdapterSP extends BaseAdapter {
    List<Sach> dss;
    Context context;

    public SachAdapterSP(List<Sach> dss, Context context) {
        this.dss = dss;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dss.size();
    }

    @Override
    public Object getItem(int i) {
        return dss.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dss.get(i).maSach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("zzzzzzzz", "getView: Vi tri phan tu " + position + (convertView == null));
        View itemView;
        if (convertView == null){
            itemView = View.inflate(parent.getContext(), R.layout.custom_adapter_sach, null);
        }else {
            itemView = convertView;
        }
        Sach sach = dss.get(position);
        TextView ten = itemView.findViewById(R.id.tenS);
        ten.setText(sach.getTenSach());
        return itemView;
    }
}
