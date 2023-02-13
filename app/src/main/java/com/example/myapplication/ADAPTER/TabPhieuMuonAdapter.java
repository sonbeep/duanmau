package com.example.myapplication.ADAPTER;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

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

public class TabPhieuMuonAdapter extends RecyclerView.Adapter<TabPhieuMuonAdapter.PhieuMuonViewHoldel> {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    PhieuMuonDao phieuMuonDao;
    private Context context;
    private ArrayList<PhieuMuon> arrayList = new ArrayList<>();
    ArrayList<ThanhVien> listTV = new ArrayList<>();
    ArrayList<Sach> listSach = new ArrayList<>();
    ArrayList<ThuThu> listTT = new ArrayList<>();
    DatePickerDialog datePickerDialog;
    Sach sach;
    ThanhVien thanhVien;
    ThuThu thuThu;

    SachDao sachDao;
    ThuThuDao thuThuDao;
    ThanhVienDao thanhVienDao;

    public TabPhieuMuonAdapter(Context context,ArrayList<PhieuMuon> arrayList ) {
        this.context = context;
        this.arrayList = arrayList;

        phieuMuonDao = new PhieuMuonDao(context);
        sachDao = new SachDao(context);
        thuThuDao = new ThuThuDao(context);
        thanhVienDao = new ThanhVienDao(context);
    }

    @NonNull
    @Override
    public PhieuMuonViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_ryc_phieu_muon,null);


        return new PhieuMuonViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHoldel holder, int position) {
        PhieuMuon phieuMuon = arrayList.get(position);
        holder.tvMaPM.setText("Mã Phiếu Mượn: "+phieuMuon.getMaPM());
        holder.tvMaTV.setText("Mã Thành Viên: "+phieuMuon.getMaTV());
        holder.tvMaSach.setText("Mã Sách: "+phieuMuon.getMaSach());
        holder.tvMaTT.setText("Mã Thủ Thư: "+phieuMuon.getMaTT());
        holder.tvNgayMuon.setText("Ngày Mượn: "+format.format(phieuMuon.getNgayMuon()));
        holder.tvTienThue.setText("Tiền Thuê: "+ phieuMuon.getTienThue());
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_sua_phieu_muon, null);
                builder.setView(v);
                AlertDialog dialog =builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText edMaPM = v.findViewById(R.id.edEditMaPhieuMuon);
                Spinner spTV = v.findViewById(R.id.spEditThanhVien);
                listTV.clear();
                listTV.addAll(thanhVienDao.getAll());
                ThanhVienAdapterSp thanhVienAdapterSp = new ThanhVienAdapterSp(listTV, context);
                spTV.setAdapter(thanhVienAdapterSp);
                Spinner spSach = v.findViewById(R.id.spEditSach);
                listSach.clear();
                listSach.addAll(sachDao.getAll());
                SachAdapterSP sachAdapterSp = new SachAdapterSP(listSach, context);
                spSach.setAdapter(sachAdapterSp);
                Spinner spTT = v.findViewById(R.id.spEditThuThu);
                listTT.clear();
                listTT.addAll(thuThuDao.getAll());
                ThuThuAdapterSp thuThuAdapterSp = new ThuThuAdapterSp(listTT, context);
                spTT.setAdapter(thuThuAdapterSp);
                EditText edNgay = v.findViewById(R.id.edEditNgayMuon);
                edNgay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar =Calendar.getInstance();
                        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                edNgay.setText(i+"-"+(i1+1)+"-"+i2);
                            }
                        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                });
                EditText edTien = v.findViewById(R.id.edEditTienThue);


                edMaPM.setText(String.valueOf(phieuMuon.getMaPM()));
                int vttv=-1;
                for (int i = 0; i<listTV.size(); i++){
                    if (listTV.get(i).getTenThanhVien().equalsIgnoreCase(thanhVienDao.getTenThanhVien(phieuMuon.getMaTV()))){
                        vttv=i;
                        break;
                    }

                }
                spTV.setSelection(vttv);
                int vts = -1;

                for (int i= 0; i<listSach.size(); i++){
                    if (listSach.get(i).getTenSach().equalsIgnoreCase(sachDao.getTenSach(phieuMuon.getMaSach()))){
                        vts=i;
                        break;
                    }
                }
                spSach.setSelection(vts);

                int vttt = -1;
                for (int i=0; i<listTT.size(); i++){
                    if (listTT.get(i).getTenThuThu().equalsIgnoreCase(thuThuDao.getTenThuThu(phieuMuon.getMaTT()))){
                        vttt=i;
                        break;
                    }
                }
                spTT.setSelection(vttt);

                edNgay.setText(format.format(phieuMuon.getNgayMuon()));
                edTien.setText(phieuMuon.getTienThue());


                Button btnSua = v.findViewById(R.id.btnEditPhieuMuon);
                Button btnHuy = v.findViewById(R.id.btnHuyEditPhieuMuon);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        phieuMuon.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        thanhVien = (ThanhVien)spTV.getSelectedItem();
                        phieuMuon.setMaTV(thanhVien.getMaThanhVien());
                        sach = (Sach) spSach.getSelectedItem();
                        phieuMuon.setMaSach(sach.getMaSach());
                        thuThu = (ThuThu) spTT.getSelectedItem();
                        phieuMuon.setMaTT(thuThu.getMaThuThu());
                        try {
                            phieuMuon.setNgayMuon(format.parse(edNgay.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        phieuMuon.setTienThue(edTien.getText().toString());

                        long a = phieuMuonDao.update(phieuMuon);
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(phieuMuonDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Sửa thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Thêm thất bại", Toast.LENGTH_LONG).show();
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
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_phieu_muon, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Button btnXoa = v.findViewById(R.id.btnXoaPhieuMuon);
                Button btnHuy = v.findViewById(R.id.btnHuyXoaPhieuMuon);
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long a = phieuMuonDao.delete(phieuMuon.getMaPM());
                        if (a>0){
                            arrayList.clear();
                            arrayList.addAll(phieuMuonDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa thành công" ,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Xóa thất bại", Toast.LENGTH_LONG).show();
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

    public class PhieuMuonViewHoldel extends RecyclerView.ViewHolder {
        TextView tvMaPM, tvMaTV, tvMaSach, tvMaTT, tvNgayMuon, tvTienThue;
        ImageView ivSua, ivXoa;

        public PhieuMuonViewHoldel(@NonNull View itemView) {
            super(itemView);
            tvMaPM = itemView.findViewById(R.id.tvMaPhieuMuon);
            tvMaTV = itemView.findViewById(R.id.tvMaThanhVien_PM);
            tvMaSach = itemView.findViewById(R.id.tvMaSach_PM);
            tvMaTT = itemView.findViewById(R.id.tvMaThuThu_PM);
            tvNgayMuon = itemView.findViewById(R.id.tvNgayMuon);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            ivSua = itemView.findViewById(R.id.ivSuaPhieuMuon);
            ivXoa = itemView.findViewById(R.id.ivXoaPhieuMuon);


        }
    }
}
