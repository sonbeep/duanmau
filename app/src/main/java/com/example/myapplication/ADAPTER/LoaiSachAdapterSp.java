package com.example.myapplication.ADAPTER;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DTO.LoaiSach;
import com.example.myapplication.R;

import java.util.List;

public class LoaiSachAdapterSp extends BaseAdapter {
    List<LoaiSach> dsls;
    Context context;

    public LoaiSachAdapterSp(List<LoaiSach> dsls, Context context) {
        this.dsls = dsls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsls.size();
    }

    @Override
    public Object getItem(int i) {
        return dsls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dsls.get(i).maLoaiSach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("zzzzzzzz", "getView: Vi tri phan tu " + position + (convertView == null));

        View itemView;
        if (convertView == null){
            itemView = View.inflate(parent.getContext(), com.example.myapplication.R.layout.custom_adapter_loaisach, null);
        }else {
            itemView = convertView;
        }
        LoaiSach loaiSach =dsls.get(position);
        TextView ten = itemView.findViewById(com.example.myapplication.R.id.ten);
        ten.setText(loaiSach.getTenLoaiSach());
        return itemView;
    }
}
