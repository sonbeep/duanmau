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

import com.example.myapplication.DAO.LoaiSachDao;
import com.example.myapplication.DTO.LoaiSach;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TabLoaiSachAdapter extends RecyclerView.Adapter<TabLoaiSachAdapter.TabLoaiSachHolder> {
    private Context context;
    private ArrayList<LoaiSach> arrayList;
    LoaiSachDao loaiSachDao;

    public TabLoaiSachAdapter(Context context, ArrayList<LoaiSach> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        loaiSachDao = new LoaiSachDao(context);
    }

    @NonNull
    @Override
    public TabLoaiSachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_ryc_loaisach,parent,false);

        return new TabLoaiSachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabLoaiSachHolder holder, int position) {
        LoaiSach loaiSach = arrayList.get(position);
        holder.tvMaLoaiSach.setText("Mã loại sách: "+loaiSach.getMaLoaiSach());
        holder.tvTenLoaiSach.setText("Tên loại sách: "+loaiSach.getTenLoaiSach());
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_sua_loaisach, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
                EditText edSuaMaLoaiSach = v.findViewById(R.id.edEditMaLoaiSach);
                EditText edSuaTenLoaiSach = v.findViewById(R.id.edEditTenLoaiSach);
                edSuaMaLoaiSach.setText(String.valueOf(loaiSach.getMaLoaiSach()));
                edSuaTenLoaiSach.setText(loaiSach.getTenLoaiSach());
                Button btnSua = v.findViewById(R.id.btnEditLoaiSach);
                Button btnHuy = v.findViewById(R.id.btnHuyEdit);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loaiSach.setMaLoaiSach(Integer.parseInt(edSuaMaLoaiSach.getText().toString()));
                        loaiSach.setTenLoaiSach(edSuaTenLoaiSach.getText().toString());
                        long a =loaiSachDao.update(loaiSach);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(loaiSachDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();
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
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_loai_sach, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Button btnXoa = v.findViewById(R.id.btnXoaLoaiSach);
                Button btnHuy = v.findViewById(R.id.btnHuyXoa);
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long a = loaiSachDao.delete(loaiSach.getMaLoaiSach());
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(loaiSachDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xoá thành công",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Xoá thất bại",Toast.LENGTH_LONG).show();
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



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TabLoaiSachHolder extends RecyclerView.ViewHolder{
        TextView tvMaLoaiSach;
        TextView tvTenLoaiSach;
        ImageView ivSua;
        ImageView ivXoa;

        public TabLoaiSachHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLoaiSach = itemView.findViewById(R.id.tvMaLoaiSach);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            ivSua = itemView.findViewById(R.id.ivSuaLoaiSach);
            ivXoa = itemView.findViewById(R.id.ivXoaLoaiSach);
        }
    }
}
