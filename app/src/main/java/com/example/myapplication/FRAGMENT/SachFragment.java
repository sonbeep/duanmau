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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ADAPTER.LoaiSachAdapterSp;
import com.example.myapplication.ADAPTER.TabSachAdapter;
import com.example.myapplication.DAO.LoaiSachDao;
import com.example.myapplication.DAO.SachDao;
import com.example.myapplication.DTO.LoaiSach;
import com.example.myapplication.DTO.Sach;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SachFragment extends Fragment {
    Button btnSach;
    RecyclerView rycSach;
    SachDao sachDao;
    ArrayList<Sach> arrayList;
    TabSachAdapter adapter;
    LoaiSachDao loaiSachDao;
    List<LoaiSach> list =new ArrayList<>();
    EditText edTim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_sach, container,false);
        btnSach = view.findViewById(R.id.fabSach);
        rycSach = view.findViewById(R.id.rycSach);
        sachDao = new SachDao(getContext());
        arrayList = new ArrayList<>();
        loaiSachDao = new LoaiSachDao(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycSach.setLayoutManager(layoutManager);
        arrayList = sachDao.getAll();
        adapter = new TabSachAdapter(getContext(), arrayList);
        rycSach.setAdapter(adapter);
        edTim = view.findViewById(R.id.edTimSach);

        btnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });

        edTim.setOnEditorActionListener((v, actionId, event) -> {
            arrayList = (ArrayList<Sach>) sachDao.getSearch(edTim.getText().toString());
            adapter = new TabSachAdapter(getContext(), arrayList);
            rycSach.setAdapter(adapter);
            return false;
        });

        return view;
    }
    public void addDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_sach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText edAddMaSach = view.findViewById(R.id.edAddMaSach);
        Spinner spThemLoaiSach= view.findViewById(R.id.spAddLoaiSach_Sach);

        list.clear();
        list.addAll(loaiSachDao.getAll());
        LoaiSachAdapterSp loaiSachAdapterSp =new LoaiSachAdapterSp(list, getContext());
        spThemLoaiSach.setAdapter(loaiSachAdapterSp);
//        list = loaiSachDao.getAll();
//        ArrayAdapter adapter_sp = new ArrayAdapter(getContext(),  android.R.layout.simple_list_item_1,list);
//        spThemLoaiSach.setAdapter(adapter_sp);
//        LoaiSach loaiSach = new LoaiSach();
//        List<HashMap< String, Object> > dsls= new ArrayList<>();
//        HashMap< String, Object> obj = new HashMap<String, Object>();
//        obj.put("maLoai",loaiSach.getMaLoaiSach() );
//        obj.put("tenLoai", loaiSach.getTenLoaiSach());
//        dsls.add(obj);
//        String[] mang_thuoc_tinh = {"maLoai", "tenLoai"};
//        int[]mang_ob = {R.id.ma, R.id.ten};
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dsls, R.layout.custom_adapter_loaisach,mang_thuoc_tinh, mang_ob);
//        spThemLoaiSach.setAdapter(simpleAdapter);
        EditText edAddTenSach = view.findViewById(R.id.edAddTenSach);
        EditText edAddGiaThue = view.findViewById(R.id.edAddGiaThue);

        Button btnAddSach = view.findViewById(R.id.btnAddSach);
        Button btnHuyAdd = view.findViewById(R.id.btnHuyAddSach);

        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edAddMaSach.getText().toString().isEmpty() || edAddTenSach.getText().toString().isEmpty() ||
                edAddGiaThue.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Các trường không được để trống ", Toast.LENGTH_LONG).show();
                    return;
                }
                if (list.size()<=0){
                    Toast.makeText(getContext(), "Loại Sách chưa có dữ liệu \n Mời bạn nhập loại sách trước ", Toast.LENGTH_LONG).show();
                    return;
                }
                Sach sach = new Sach();
                try {
                    Integer.parseInt(edAddMaSach.getText().toString());
                    sach.setMaSach(Integer.parseInt(edAddMaSach.getText().toString()));
                    LoaiSach loaiSach = (LoaiSach)spThemLoaiSach.getSelectedItem();
                    sach.setMaLoaiSach(loaiSach.getMaLoaiSach());
                    sach.setTenSach(edAddTenSach.getText().toString());
                    sach.setGiaThue(edAddGiaThue.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Mã sách cần nhập số ", Toast.LENGTH_LONG).show();

                }

                long a = sachDao.insert(sach);
                if (a>0){
                    arrayList.clear();
                    arrayList.addAll(sachDao.getAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Thêm thành công ", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(),"Thêm thất bại  ", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnHuyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
