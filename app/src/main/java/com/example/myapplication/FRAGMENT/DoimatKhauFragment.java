package com.example.myapplication.FRAGMENT;

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

import com.example.myapplication.ADAPTER.TabThanhVienAdapter;
import com.example.myapplication.ADAPTER.TabThuThuAdapter;
import com.example.myapplication.DAO.ThuThuDao;
import com.example.myapplication.DTO.ThuThu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class DoimatKhauFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_doi_mat_khau, container, false);
        EditText edPassCu = view.findViewById(R.id.edOldPass);
        EditText edPassMoi = view.findViewById(R.id.edNewPass);
        EditText edNhapLaiPass = view.findViewById(R.id.edRePass);
        Button btnDoi = view.findViewById(R.id.btnDoi);
        String passCu = edPassCu.getText().toString();
        String passMoi = edPassMoi.getText().toString();
        String rePass = edNhapLaiPass.getText().toString();
        ThuThu thuThu = new ThuThu();
        ThuThuDao thuThuDao = new ThuThuDao(getContext());
        ArrayList<ThuThu> arrayList = new ArrayList<>();
        TabThuThuAdapter adapter = new TabThuThuAdapter(getContext(), arrayList);

        btnDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edPassCu.getText().toString().matches(String.valueOf(thuThu.getMaThuThu()))==false){
                    Toast.makeText(getContext(), "Mã thủ thư không tồn tại", Toast.LENGTH_LONG).show();
                    return;
                }

                    thuThu.setMaThuThu(Integer.parseInt(edPassCu.getText().toString()));
                    thuThu.setTenThuThu(edPassMoi.getText().toString());
                    thuThu.setMatKhau(edNhapLaiPass.getText().toString());
                    long a = thuThuDao.update(thuThu);
                    if (a>0){
                        arrayList.clear();
                        arrayList.addAll(thuThuDao.getAll());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();


                }
            }
        });
        return view;
    }
}
