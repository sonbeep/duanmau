package com.example.myapplication.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.PhieuMuonDao;
import com.example.myapplication.DTO.PhieuMuon;
import com.example.myapplication.R;

import java.util.ArrayList;

public class DoanhThuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_doanh_thu, container, false);
        int kq=0;
        TextView tv= view.findViewById(R.id.tvDoanhThu);
        PhieuMuonDao dao = new PhieuMuonDao(getContext());
        ArrayList<PhieuMuon> list = dao.getAll();
        for (int i=0; i<list.size(); i++){
            kq = kq + Integer.parseInt(list.get(i).getTienThue());
        }
        tv.setText("Doanh thu: "+kq+" VND");
        return view;
    }
}
