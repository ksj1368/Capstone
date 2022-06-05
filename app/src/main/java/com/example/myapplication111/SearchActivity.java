package com.example.myapplication111;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SearchActivity extends AppCompatActivity {
    String Room,Hool,LAT,LON;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private NavigationFragment nfragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);
        Room = getIntent().getStringExtra("Room");
        Hool = getIntent().getStringExtra("Hool");
        LAT = getIntent().getStringExtra("LAT");
        LON = getIntent().getStringExtra("LON");

        FragmentManager fragmentManager=getSupportFragmentManager();
        nfragment = new NavigationFragment();

        Bundle bundle = new Bundle();
        bundle.putString("lat",LAT);
        bundle.putString("lon",LON);
        nfragment.setArguments(bundle);

        // 팝업 창
        new AlertDialog.Builder(this)
                .setTitle("알람 팝업")
                .setMessage(Room + "으로 안내하겠습니다.")
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                    }
                })
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){

                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_frameLayout, nfragment).commitAllowingStateLoss();
                    }
                })
                .show(); // 팝업창 보여줌
    }
}