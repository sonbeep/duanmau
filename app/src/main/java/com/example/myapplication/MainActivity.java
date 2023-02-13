package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.FRAGMENT.DoanhThuFragment;
import com.example.myapplication.FRAGMENT.DoimatKhauFragment;
import com.example.myapplication.FRAGMENT.HomeFragment;
import com.example.myapplication.FRAGMENT.LoaiSachFragment;
import com.example.myapplication.FRAGMENT.PhieuMuonFragment;
import com.example.myapplication.FRAGMENT.SachFragment;
import com.example.myapplication.FRAGMENT.ThanhVienFragment;
import com.example.myapplication.FRAGMENT.ThuThuFragment;
import com.example.myapplication.FRAGMENT.Top10Fragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    PhieuMuonFragment phieuMuonFragment;
    DoanhThuFragment doanhThuFragment;
    DoimatKhauFragment doimatKhauFragment;
    LoaiSachFragment loaiSachFragment;
    SachFragment sachFragment;
    ThanhVienFragment thanhVienFragment;
    ThuThuFragment thuThuFragment;
    Top10Fragment top10Fragment;
    NavigationView navigationView;
    HomeFragment homeFragment;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phieuMuonFragment = new PhieuMuonFragment();
        doanhThuFragment = new DoanhThuFragment();
        doimatKhauFragment = new DoimatKhauFragment();
        loaiSachFragment = new LoaiSachFragment();
        sachFragment = new SachFragment();
        thanhVienFragment = new ThanhVienFragment();
        top10Fragment = new Top10Fragment();
        thuThuFragment = new ThuThuFragment();
        homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frag_container, homeFragment).commit();
        intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, homeFragment).commit();
                        break;
                    case R.id.nav_phieuMuon:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, phieuMuonFragment).commit();
                        break;
                    case R.id.nav_loaiSach:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, loaiSachFragment).commit();
                        break;
                    case R.id.nav_sach:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, sachFragment).commit();
                        break;
                    case R.id.nav_quanLyThanhVien:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, thanhVienFragment).commit();
                        break;
                    case R.id.nav_top10:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, top10Fragment).commit();
                        break;
                    case R.id.nav_doanhThu:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, doanhThuFragment).commit();
                        break;

                    case R.id.nav_ThuThu:
                        fragmentManager.beginTransaction().replace(R.id.frag_container, thuThuFragment).commit();
                        break;
                    case R.id.nav_dangXuat:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);


                }
                mDrawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mDrawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }
}