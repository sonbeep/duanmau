package com.example.myapplication.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.PhieuMuonDao;
import com.example.myapplication.DTO.PhieuMuon;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Top10Fragment extends Fragment {
    ListView listView;
    PhieuMuonDao phieuMuonDao;
    List<PhieuMuon> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_top10, container, false);
        listView = view.findViewById(R.id.lvTop);
        phieuMuonDao = new PhieuMuonDao(getContext());
        arrayList = new ArrayList<>();


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        arrayList.addAll(phieuMuonDao.top10());
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
