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

import com.example.myapplication.ADAPTER.TabThuThuAdapter;
import com.example.myapplication.DAO.ThuThuDao;
import com.example.myapplication.DTO.ThuThu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ThuThuFragment extends Fragment {
    Button fabThem;
    RecyclerView ryc;
    ThuThuDao thuThuDao;
    ArrayList<ThuThu> arrayList;
    TabThuThuAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_thu_thu, container, false);

        fabThem = view.findViewById(R.id.fabThuThu);
        ryc = view.findViewById(R.id.rycThuThu);
        thuThuDao = new ThuThuDao(getContext());
        arrayList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ryc.setLayoutManager(layoutManager);
        arrayList = thuThuDao.getAll();
        adapter = new TabThuThuAdapter(getContext(), arrayList);
        ryc.setAdapter(adapter);

        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_thu_thu, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText edMa = view1.findViewById(R.id.edAddMaThuThu);
                EditText edTen = view1.findViewById(R.id.edAddTenThuThu);
                EditText edMatKhau = view1.findViewById(R.id.edAddMatKhau);

                Button btnThem = view1.findViewById(R.id.btnAddThuThu);
                Button btnHuy = view1.findViewById(R.id.btnHuyAddThuThu);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ThuThu thuThu = new ThuThu();
                        try {
                            Integer.parseInt(edMa.getText().toString());
                            thuThu.setMaThuThu(Integer.parseInt(edMa.getText().toString()));
                            thuThu.setTenThuThu(edTen.getText().toString());
                            thuThu.setMatKhau(edMatKhau.getText().toString());
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Mã Thủ Thư phải là số nguyên" , Toast.LENGTH_LONG).show();
                        }

                        long a = thuThuDao.insert(thuThu);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(thuThuDao.getAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
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
        });




        return view;
    }
}
