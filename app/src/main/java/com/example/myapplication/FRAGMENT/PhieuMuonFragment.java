package com.example.myapplication.FRAGMENT;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ADAPTER.SachAdapterSP;
import com.example.myapplication.ADAPTER.TabPhieuMuonAdapter;
import com.example.myapplication.ADAPTER.ThanhVienAdapterSp;
import com.example.myapplication.ADAPTER.ThuThuAdapterSp;
import com.example.myapplication.DAO.PhieuMuonDao;
import com.example.myapplication.DAO.SachDao;
import com.example.myapplication.DAO.ThanhVienDao;
import com.example.myapplication.DAO.ThuThuDao;
import com.example.myapplication.DTO.PhieuMuon;
import com.example.myapplication.DTO.Sach;
import com.example.myapplication.DTO.ThanhVien;
import com.example.myapplication.DTO.ThuThu;
import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhieuMuonFragment extends Fragment {
    Button fabPM;
    RecyclerView ryc;
    ThanhVienDao thanhVienDao;
    SachDao sachDao;
    ThuThuDao thuThuDao;
    PhieuMuonDao phieuMuonDao;
    ArrayList<PhieuMuon> phieuMuonArrayList;

    TabPhieuMuonAdapter adapter;
    List<ThanhVien> listTV= new ArrayList<>();
    List<Sach> listSach= new ArrayList<>();
    List<ThuThu> listTT= new ArrayList<>();
    DatePickerDialog datePickerDialog;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_phieu_muon, container,false);
        ryc = view.findViewById(R.id.rycPhieuMuon);
        fabPM = view.findViewById(R.id.fabPhieuMuon);
        phieuMuonDao = new PhieuMuonDao(getContext());
        phieuMuonArrayList = new ArrayList<>();
        thanhVienDao = new ThanhVienDao(getContext());
        sachDao = new SachDao(getContext());
        thuThuDao = new ThuThuDao(getContext());



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ryc.setLayoutManager(layoutManager);
        adapter = new TabPhieuMuonAdapter(getContext(), phieuMuonArrayList);
        ryc.setAdapter(adapter);

        fabPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDialog();
            }
        });


        return view;
    }
    public void AddDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_phieu_muon, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText edMaPM = view.findViewById(R.id.edAddMaPhieuMuon);
        Spinner spTV = view.findViewById(R.id.spAddThanhVien);
        listTV.clear();
        listTV.addAll(thanhVienDao.getAll());
        ThanhVienAdapterSp thanhVienAdapterSp = new ThanhVienAdapterSp(listTV, getContext());
        spTV.setAdapter(thanhVienAdapterSp);
        Spinner spSach = view.findViewById(R.id.spAddSach);
        listSach.clear();
        listSach.addAll(sachDao.getAll());
        SachAdapterSP sachAdapterSp = new SachAdapterSP(listSach, getContext());
        spSach.setAdapter(sachAdapterSp);
        Spinner spTT = view.findViewById(R.id.spAddThuThu);
        listTT.clear();
        listTT.addAll(thuThuDao.getAll());
        ThuThuAdapterSp thuThuAdapterSp = new ThuThuAdapterSp(listTT, getContext());
        spTT.setAdapter(thuThuAdapterSp);

        EditText edNgayMuon = view.findViewById(R.id.edAddNgayMuon);
        EditText edTienThue = view.findViewById(R.id.edAddTienThue);

        edNgayMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edNgayMuon.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Button btnThem = view.findViewById(R.id.btnAddPhieuMuon);
        Button btnHuy = view.findViewById(R.id.btnHuyAddPhieuMuon);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edMaPM.getText().toString().isEmpty() || edNgayMuon.getText().toString().isEmpty()||
                edTienThue.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Các trường không được để trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (listTV.size()<=0){
                    Toast.makeText(getContext(), "Chưa có thành viên nào \n Vui lòng thêm thành viên trước", Toast.LENGTH_LONG).show();
                    return;

                }
                if (listSach.size()<=0){
                    Toast.makeText(getContext(), "Chưa có sách nào \n Vui lòng thêm sách trước", Toast.LENGTH_LONG).show();
                    return;
                }
                if (listTT.size()<=0){
                    Toast.makeText(getContext(), "Chưa có thủ thư \n  Vui lòng thêm thu thư trước", Toast.LENGTH_LONG).show();
                    return;
                }
                PhieuMuon phieuMuon = new PhieuMuon();

                try {
                    Integer.parseInt(edMaPM.getText().toString());
                    phieuMuon.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    ThanhVien thanhVien = (ThanhVien) spTV.getSelectedItem();
                    phieuMuon.setMaTV(thanhVien.getMaThanhVien());
                    Sach sach = (Sach) spSach.getSelectedItem();
                    phieuMuon.setMaSach(sach.getMaSach());
                    ThuThu thuThu = (ThuThu) spTT.getSelectedItem();
                    phieuMuon.setMaTT(thuThu.getMaThuThu());
                    try {
                        phieuMuon.setNgayMuon(format.parse(edNgayMuon.getText().toString()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    phieuMuon.setTienThue(edTienThue.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Mã Phiếu Mượn cần nhập số", Toast.LENGTH_LONG).show();
                }
                long a = phieuMuonDao.insert(phieuMuon);
                if (a>0){
                    phieuMuonArrayList.clear();
                    phieuMuonArrayList.addAll(phieuMuonDao.getAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Thêm thành công", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(),"Thêm thất bại", Toast.LENGTH_LONG).show();
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
