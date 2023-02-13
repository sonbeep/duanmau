package com.example.myapplication.FRAGMENT;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ADAPTER.TabLoaiSachAdapter;
import com.example.myapplication.DAO.LoaiSachDao;
import com.example.myapplication.DTO.LoaiSach;
import com.example.myapplication.R;


import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    LoaiSachDao loaiSachDao;
    Button fabLoaiSach;
    RecyclerView rycLoaiSach;
    TabLoaiSachAdapter adapter;
    ArrayList<LoaiSach> arrayList;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_loai_sach, container, false);
        rycLoaiSach = view.findViewById(R.id.rycLoaiSach);
        fabLoaiSach = view.findViewById(R.id.fabLoaiSach);
        loaiSachDao = new LoaiSachDao(getContext());
        arrayList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycLoaiSach.setLayoutManager(layoutManager);
        arrayList = loaiSachDao.getAll();
        adapter = new TabLoaiSachAdapter(getContext(), arrayList);
        rycLoaiSach.setAdapter(adapter);

        fabLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });







        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_loai_sach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
        EditText edAddMaLoaiSach = view.findViewById(R.id.edAddMaLoaiSach);
        EditText edAddTenLoaiSach = view.findViewById(R.id.edAddTenLoaiSach);
        Button btnAdd = view.findViewById(R.id.btnAddLoaiSach);
        Button btnHuy = view.findViewById(R.id.btnHuyAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = new LoaiSach();
                if (edAddMaLoaiSach.getText().toString().isEmpty() && edAddTenLoaiSach.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống các trường", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Integer.parseInt(edAddMaLoaiSach.getText().toString());
                    loaiSach.setMaLoaiSach(Integer.parseInt(edAddMaLoaiSach.getText().toString()));
                    loaiSach.setTenLoaiSach(edAddTenLoaiSach.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Mã Loại Sách phải là số nguyên", Toast.LENGTH_LONG).show();
                }


                long a = loaiSachDao.insert(loaiSach);
                if (a>0){
                    arrayList.clear();
                    arrayList.addAll(loaiSachDao.getAll());
                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
