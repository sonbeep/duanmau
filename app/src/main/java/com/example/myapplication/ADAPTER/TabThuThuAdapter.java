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

import com.example.myapplication.DAO.ThuThuDao;
import com.example.myapplication.DTO.ThuThu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TabThuThuAdapter extends RecyclerView.Adapter<TabThuThuAdapter.ThuThuViewHoldel> {
    Context context;
    ArrayList<ThuThu> arrayList;
    ThuThuDao thuThuDao;

    public TabThuThuAdapter(Context context, ArrayList<ThuThu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        thuThuDao = new ThuThuDao(context);
    }

    @NonNull
    @Override
    public ThuThuViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_ryc_thu_thu, null);
        return new ThuThuViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHoldel holder, int position) {
        ThuThu thuThu = arrayList.get(position);
        holder.tvMa.setText("Mã thủ thư: "+thuThu.getMaThuThu());
        holder.tvTen.setText("Tên thủ thư: "+thuThu.getTenThuThu());
        holder.tvMatKhau.setText("Mật Khẩu: "+thuThu.getMatKhau());
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_sua_thuthu, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText edSuaMa = view1.findViewById(R.id.edEditMaThuThu);
                EditText edSuaTen = view1.findViewById(R.id.edEditTenThuThu);
                EditText edSuaMatKhau = view1.findViewById(R.id.edEditMatKhau);
                edSuaMa.setText(String.valueOf(thuThu.getMaThuThu()));
                edSuaTen.setText(thuThu.getTenThuThu());
                edSuaMatKhau.setText(thuThu.getMatKhau());

                Button btnSua = view1.findViewById(R.id.btnEditThuThu);
                Button btnHuy = view1.findViewById(R.id.btnHuyEditThuThu);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thuThu.setMaThuThu(Integer.parseInt(edSuaMa.getText().toString()));
                        thuThu.setTenThuThu(edSuaTen.getText().toString());
                        thuThu.setMatKhau(edSuaMatKhau.getText().toString());
                        long a = thuThuDao.update(thuThu);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(thuThuDao.getAll());
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
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_thu_thu, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button btnXoa = v.findViewById(R.id.btnXoaThuThu);
                Button btnHuy = v.findViewById(R.id.btnHuyXoaThuThu);

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long a = thuThuDao.delete(thuThu.getMaThuThu());
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(thuThuDao.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_LONG).show();
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

    public class ThuThuViewHoldel extends RecyclerView.ViewHolder {
        TextView tvMa, tvTen, tvMatKhau;
        ImageView  ivXoa, ivSua;
        public ThuThuViewHoldel(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tvMaThuThu);
            tvTen = itemView.findViewById(R.id.tvTenThuThu);
            tvMatKhau = itemView.findViewById(R.id.tvMatKhau);
            ivSua = itemView.findViewById(R.id.ivSuaThuThu);
            ivXoa = itemView.findViewById(R.id.ivXoaThuThu);


        }
    }
}
