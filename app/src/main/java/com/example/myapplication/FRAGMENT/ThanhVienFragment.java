package com.example.myapplication.FRAGMENT;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ADAPTER.TabThanhVienAdapter;
import com.example.myapplication.DAO.ThanhVienDao;
import com.example.myapplication.DTO.ThanhVien;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    RecyclerView recyclerView;
    ThanhVienDao thanhVienDao;
    ArrayList<ThanhVien> arrayList;
    TabThanhVienAdapter adapter;
    Button btnThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_thanh_vien, container, false);
        recyclerView = view.findViewById(R.id.rycThanhVien);
        btnThanhVien = view.findViewById(R.id.fabThanhVien);
        thanhVienDao = new ThanhVienDao(getContext());
        arrayList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = thanhVienDao.getAll();
        adapter = new TabThanhVienAdapter(getContext(), arrayList);
        recyclerView.setAdapter(adapter);

        btnThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });
        return view;
    }
    public void addDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_thanh_vien, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText edMaThanhVien = view.findViewById(R.id.edAddMaThanhVien);
        EditText edTenThanhVien = view.findViewById(R.id.edAddTenThanhVien);
        EditText edNamSinh = view.findViewById(R.id.edAddNamSinhThanhVien);
        EditText edStk = view.findViewById(R.id.edAddSoTaiKhoan);
        Button btnAdd = view.findViewById(R.id.btnAddThanhVien);
        Button btnHuy = view.findViewById(R.id.btnHuyAddThanhVien);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien thanhVien = new ThanhVien();
                if (edMaThanhVien.getText().toString().isEmpty() && edTenThanhVien.getText().toString().isEmpty() && edNamSinh.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Không được để trống các trường ", Toast.LENGTH_LONG).show();
                    return;
                }


                try {
                    Integer.parseInt(edMaThanhVien.getText().toString());
                    thanhVien.setMaThanhVien(Integer.parseInt(edMaThanhVien.getText().toString()));
                    thanhVien.setTenThanhVien(edTenThanhVien.getText().toString());
                    thanhVien.setNamSinh(edNamSinh.getText().toString());
                    thanhVien.setSoTaiKhoan(edStk.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Mã thành viên cần nhập số ", Toast.LENGTH_LONG).show();
                }

                long a = thanhVienDao.insert(thanhVien);
                if (a>0){
                    arrayList.clear();
                    arrayList.addAll(thanhVienDao.getAll());

                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
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
