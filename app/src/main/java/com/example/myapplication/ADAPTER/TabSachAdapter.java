package com.example.myapplication.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.LoaiSachDao;
import com.example.myapplication.DAO.SachDao;
import com.example.myapplication.DTO.LoaiSach;
import com.example.myapplication.DTO.Sach;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TabSachAdapter extends RecyclerView.Adapter<TabSachAdapter.TabSachHoldel> {
    private Context context;
    private ArrayList<Sach> arrayList;
    ArrayList<LoaiSach> list =new ArrayList<>();
     SachDao sachDao;
     LoaiSach loaiSach;
     LoaiSachDao loaiSachDao;

    public TabSachAdapter(Context context, ArrayList<Sach> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sachDao = new SachDao(context);
        loaiSachDao = new LoaiSachDao(context);
    }

    @NonNull
    @Override
    public TabSachHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_ryc_sach, null);
        return new TabSachHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabSachHoldel holder, int position) {
        Sach sach = arrayList.get(position);
        holder.tvMaSach.setText("Mã Sách: "+sach.getMaSach());
        holder.tvMaLoai.setText("Mã Loại Sách: "+sach.getMaLoaiSach());
        holder.tvTenSach.setText("Tên Sách: "+sach.getTenSach());
        holder.tvGiaThue.setText("Số lượng: "+sach.getGiaThue());

        holder.tvGiaThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_so_luong, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                TextView tvSuaTenSach = view.findViewById(R.id.tvSuaTenSach);
                EditText edSuaSoLuong = view.findViewById(R.id.edSuaSoLuong);
                Button btnSua = view.findViewById(R.id.btnSuaSoLuong);
                Button btnHUy = view.findViewById(R.id.btnHuySuaSoLuong);
                tvSuaTenSach.setText(sach.getTenSach());
                edSuaSoLuong.setText(sach.getGiaThue());

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sach.setGiaThue(edSuaSoLuong.getText().toString());
                        long a = sachDao.update(sach);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(sachDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa thành công ", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Sửa thất bại ", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_sach, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText edSuaMaSach = view.findViewById(R.id.edEditMaSach);
                Spinner spinner = view.findViewById(R.id.spEditLoaiSach_Sach);

                list.clear();
                list.addAll(loaiSachDao.getAll());
                LoaiSachAdapterSp loaiSachAdapterSp =new LoaiSachAdapterSp(list, context);
                spinner.setAdapter(loaiSachAdapterSp);
//                list = loaiSachDao.getAll();
//                ArrayAdapter adapter_sp= new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, list);
//                spinner.setAdapter(adapter_sp);
                EditText edSuaTenSach = view.findViewById(R.id.edEditTenSach);
                EditText edSuaGiaThue = view.findViewById(R.id.edEditGiaThue);

                edSuaMaSach.setText(sach.getMaSach()+"");
                int vitri = -1;
                for (int i=0; i<list.size(); i++){
                    if (list.get(i).getTenLoaiSach().equalsIgnoreCase(loaiSachDao.getTenLoaiSach(sach.getMaLoaiSach()))){
                        vitri = i;
                        break;
                    }
                }
                spinner.setSelection(vitri);
                edSuaTenSach.setText(sach.getTenSach());
                edSuaGiaThue.setText(sach.getGiaThue());

                Button btnSua = view.findViewById(R.id.btnEditSach);
                Button btnHUy = view.findViewById(R.id.btnHuyEditSach);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sach.setMaSach(Integer.parseInt(edSuaMaSach.getText().toString()));
                        loaiSach= (LoaiSach)spinner.getSelectedItem();
                        sach.setMaLoaiSach(loaiSach.getMaLoaiSach());
                        sach.setTenSach(edSuaTenSach.getText().toString());
                        sach.setGiaThue(edSuaGiaThue.getText().toString());
                        long a = sachDao.update(sach);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(sachDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa thành công ", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Sửa thất bại ", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });
                btnHUy.setOnClickListener(new View.OnClickListener() {
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
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_sach, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button btnXoa = v.findViewById(R.id.btnXoaSach);
                Button btnHuy = v.findViewById(R.id.btnHuyXoaSach);

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long a = sachDao.delete(sach.getMaSach());
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(sachDao.getAll());
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

    public class TabSachHoldel extends RecyclerView.ViewHolder {
        TextView tvMaSach, tvMaLoai, tvTenSach, tvGiaThue;
        ImageView ivSua, ivXoa;
        public TabSachHoldel(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvgiaThue);
            ivSua = itemView.findViewById(R.id.ivSuaSach);
            ivXoa = itemView.findViewById(R.id.ivXoaSach);

        }
    }
}
