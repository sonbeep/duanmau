package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DTO.Sach;
import com.example.myapplication.DTO.ThanhVien;
import com.example.myapplication.R;

import java.util.List;

public class ThanhVienAdapterSp extends BaseAdapter {
    List<ThanhVien> dstv;
    Context context;

    public ThanhVienAdapterSp(List<ThanhVien> dstv, Context context) {
        this.dstv = dstv;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dstv.size();
    }

    @Override
    public Object getItem(int i) {
        return dstv.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dstv.get(i).maThanhVien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("zzzzzzzz", "getView: Vi tri phan tu " + position + (convertView == null));
        View itemView;
        if (convertView ==null){
            itemView = View.inflate(parent.getContext(), R.layout.custom_adapter_thanh_vien, null);
        }else {
            itemView = convertView;
        }
        ThanhVien thanhVien  = dstv.get(position);
        TextView ten = itemView.findViewById(R.id.tenAdapterTV);
        ten.setText(thanhVien.getTenThanhVien());

        return itemView;
    }
}
