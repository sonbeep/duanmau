package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DTO.Sach;
import com.example.myapplication.DTO.ThuThu;
import com.example.myapplication.R;

import java.util.List;

public class ThuThuAdapterSp extends BaseAdapter {
    List<ThuThu> dstt;
    Context context;

    public ThuThuAdapterSp(List<ThuThu> dstt, Context context) {
        this.dstt = dstt;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dstt.size();
    }

    @Override
    public Object getItem(int i) {
        return dstt.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dstt.get(i).maThuThu;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("zzzzzzzz", "getView: Vi tri phan tu " + i + (view == null));
        View itemView;
        if (view ==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.custom_adapter_thuthu, null);
        }else {
            itemView = view;
        }
        ThuThu thuThu  = dstt.get(i);
        TextView ten = itemView.findViewById(R.id.tenAdapterTT);
        ten.setText(thuThu.getTenThuThu());

        return itemView;
    }
}
