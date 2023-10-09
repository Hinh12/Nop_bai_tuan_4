package com.example.mob2041_hinhnvph42207;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mob2041_hinhnvph42207.Frame.ChangePassFragment;
import com.example.mob2041_hinhnvph42207.Frame.frgaddUser;
import com.example.mob2041_hinhnvph42207.Frame.frgdoanhthu;
import com.example.mob2041_hinhnvph42207.Frame.frgloaisach;
import com.example.mob2041_hinhnvph42207.Frame.frglogout;
import com.example.mob2041_hinhnvph42207.Frame.frgphieumuon;
import com.example.mob2041_hinhnvph42207.Frame.frgsach;
import com.example.mob2041_hinhnvph42207.Frame.frgthanhvien;
import com.example.mob2041_hinhnvph42207.Frame.frgtop;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final int FRAMENINT_FRGPHIEU_MUON = 0;
    public static final int FRAMENINT_FRGTHANH_VIEN = 1;
    public static final int FRAMENINT_FRGLOAI_SACH = 2;
    public static final int FRAMENINT_FRGSACH = 3;
    public static final int FRAMENINT_FRGTOP = 4;
    public static final int FRAMENINT_FRGDOANG_THU = 5;
    public static final int FRAMENINT_FRGADDUSER = 6;
    public static final int FRAMENINT_FRGCHANGEPASSFRAGMENT = 7;
    public static final int FRAMENINT_FRGLOGOUT = 8;




    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    private int currentFrament = FRAMENINT_FRGPHIEU_MUON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toollbar);
        nav=findViewById(R.id.nvView);
        nav.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_PhieuMuon){
                    frgphieumuon frgphieumuon = new frgphieumuon();
                    replaceFtg(frgphieumuon);
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                    frgloaisach frgloaisach = new frgloaisach();
                    replaceFtg(frgloaisach);
                } else if (item.getItemId() == R.id.nav_Sach) {
                    frgsach frgsach = new frgsach();
                    replaceFtg(frgsach);
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    frgthanhvien frgthanhvien = new frgthanhvien();
                    replaceFtg(frgthanhvien);
                } else if (item.getItemId() == R.id.sub_Top) {
                    frgtop frgtop = new frgtop();
                    replaceFtg(frgtop);
                } else if (item.getItemId() == R.id.sub_DoanhThu) {
                    frgdoanhthu frgdoanhthu = new frgdoanhthu();
                    replaceFtg(frgdoanhthu);
                } else if (item.getItemId() == R.id.sub_AddUser) {
                    frgaddUser frgaddUser = new frgaddUser();
                    replaceFtg(frgaddUser);
                } else if (item.getItemId() == R.id.sub_Pass) {
                    setTitle("Thay đổi mật khẩu");
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    replaceFtg(changePassFragment);
                }
                else if (item.getItemId() == R.id.sub_Logout) {
                    frglogout frglogout = new frglogout();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    replaceFtg(frglogout);

                }
                drawerLayout.closeDrawers();
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });
    }
    private void replaceFtg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flContent,frg).commit();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.nav_PhieuMuon){
            if (currentFrament != FRAMENINT_FRGPHIEU_MUON){
                replaceFtg(new frgphieumuon());
                toolbar.setTitle("Phiếu Mượn");
                currentFrament = FRAMENINT_FRGPHIEU_MUON;
            }

        }else if (id == R.id.nav_LoaiSach) {
            if (currentFrament != FRAMENINT_FRGLOAI_SACH){
                replaceFtg(new frgloaisach());
                toolbar.setTitle("Loại Sách");
                currentFrament = FRAMENINT_FRGLOAI_SACH;
            }

        } else if (id == R.id.nav_Sach) {
            if (currentFrament != FRAMENINT_FRGSACH){
                replaceFtg(new frgsach());
                toolbar.setTitle("Sách");
                currentFrament = FRAMENINT_FRGSACH;
            }

        } else if (id == R.id.nav_ThanhVien) {
            if (currentFrament != FRAMENINT_FRGTHANH_VIEN){
                replaceFtg(new frgthanhvien());
                toolbar.setTitle("Thành Viên");
                currentFrament = FRAMENINT_FRGTHANH_VIEN;
            }

        } else if (id == R.id.sub_Top) {
            if (currentFrament != FRAMENINT_FRGTOP){
                replaceFtg(new frgtop());
                toolbar.setTitle("Top loại sách bán chạy");
                currentFrament = FRAMENINT_FRGTOP;
            }

        } else if (id == R.id.sub_DoanhThu) {
            if (currentFrament != FRAMENINT_FRGDOANG_THU){
                replaceFtg(new frgdoanhthu());
                toolbar.setTitle("Doang thu");
                currentFrament = FRAMENINT_FRGDOANG_THU;
            }

        } else if (id == R.id.sub_AddUser) {
            if (currentFrament != FRAMENINT_FRGADDUSER){
                replaceFtg(new frgaddUser());
                toolbar.setTitle("Thêm thành viên");
                currentFrament = FRAMENINT_FRGADDUSER;
            }

        } else if (id == R.id.sub_Pass) {
            if (currentFrament != FRAMENINT_FRGCHANGEPASSFRAGMENT){
                replaceFtg(new ChangePassFragment());
                toolbar.setTitle("Đổi mật khẩu");
                currentFrament = FRAMENINT_FRGCHANGEPASSFRAGMENT;
            }

        } else if (id == R.id.sub_Logout) {
            if (currentFrament != FRAMENINT_FRGLOGOUT){
                replaceFtg(new frglogout());
                toolbar.setTitle("Đăng xuất");
                currentFrament = FRAMENINT_FRGLOGOUT;
            }

        }

        return true;
    }
}