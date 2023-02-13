package com.example.myapplication.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.ThanhVienDao;
import com.example.myapplication.DTO.ThanhVien;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TabThanhVienAdapter extends RecyclerView.Adapter<TabThanhVienAdapter.TabThanhVienViewHoldel> {
    private Context context;
    private ArrayList<ThanhVien> arrayList;
    ThanhVienDao thanhVienDao;

    public TabThanhVienAdapter(Context context, ArrayList<ThanhVien> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        thanhVienDao = new ThanhVienDao(context);
    }

    @NonNull
    @Override
    public TabThanhVienViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_ryc_thanhvien, null);

        return new TabThanhVienViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabThanhVienViewHoldel holder, int position) {
        ThanhVien thanhVien = arrayList.get(position);
        holder.tvMaThanh.setText("Mã thành viên: "+thanhVien.getMaThanhVien());
        holder.tvTenThanhVien.setText("Tên thành viên: "+thanhVien.getTenThanhVien());
        holder.tvNamSinhThanhVien.setText("Năm sinh: "+thanhVien.getNamSinh());
        holder.tvSoTaiKhoan.setText("Số tài khoản: "+thanhVien.getSoTaiKhoan());

        if (Integer.parseInt(thanhVien.getSoTaiKhoan()) % 5 ==0 ){
            holder.tvSoTaiKhoan.setTextColor(Color.BLACK);
            holder.tvSoTaiKhoan.setTextSize(30);
        }
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_sua_thanhvien, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText edSuaMaThanhVien = v.findViewById(R.id.edEditMaThanhVien);
                EditText edSuaTenThanhVien = v.findViewById(R.id.edEditTenThanhVien);
                EditText edSuaNamSinhThanhVien = v.findViewById(R.id.edEditNamSinhThanhVien);
                edSuaMaThanhVien.setText(String.valueOf(thanhVien.getMaThanhVien()));
                edSuaTenThanhVien.setText(thanhVien.getTenThanhVien());
                edSuaNamSinhThanhVien.setText(thanhVien.getNamSinh());
                Button btnSua = v.findViewById(R.id.btnEditThanhVien);
                Button btnHuy = v.findViewById(R.id.btnHuyEditThanhVien);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thanhVien.setMaThanhVien(Integer.parseInt(edSuaMaThanhVien.getText().toString()));
                        thanhVien.setTenThanhVien(edSuaTenThanhVien.getText().toString());
                        thanhVien.setNamSinh(edSuaNamSinhThanhVien.getText().toString());
                        long a = thanhVienDao.update(thanhVien);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(thanhVienDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();

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
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_thanh_vien, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Button btnXoa = v.findViewById(R.id.btnXoaThanhVien);
                Button btnHuyXoa = v.findViewById(R.id.btnHuyXoaThanhVien);

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long a = thanhVienDao.delete(thanhVien.getMaThanhVien());
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(thanhVienDao.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
                btnHuyXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TabThanhVienViewHoldel extends RecyclerView.ViewHolder{
        TextView tvMaThanh, tvTenThanhVien, tvNamSinhThanhVien, tvSoTaiKhoan;
        ImageView ivSua, ivXoa;

        public TabThanhVienViewHoldel(@NonNull View itemView) {
            super(itemView);
            tvMaThanh = itemView.findViewById(R.id.tvMaThanhVien);
            tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
            tvNamSinhThanhVien = itemView.findViewById(R.id.tvNamSinhThanhVien);
            tvSoTaiKhoan = itemView.findViewById(R.id.tvSoTaiKhoan);

            ivSua = itemView.findViewById(R.id.ivSuaThanhVien);
            ivXoa = itemView.findViewById(R.id.ivXoaThanhVien);

        }
    }
}
