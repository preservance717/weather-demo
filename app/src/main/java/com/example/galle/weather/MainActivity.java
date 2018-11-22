package com.example.galle.weather;

import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.galle.weather.Fragment.AboutFragment;
import com.example.galle.weather.Fragment.HomeFragment;
import com.example.galle.weather.Fragment.SettingFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public static final int SHOW_RESPONSE = 0;

    private LinearLayout mTabHome;
    private LinearLayout mTabAbout;
    private LinearLayout mTabSetting;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;

    private HomeFragment mFragHome;
    private AboutFragment mFragAbout;
    private SettingFragment mFragSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();

        setTitle("Home");
        MainActivity.this.resetImgs();
        if (mFragHome == null) {
            mFragHome = new HomeFragment();
        } else {

        }
        getFragmentManager().beginTransaction().replace(R.id.id_content, mFragHome).commitAllowingStateLoss();
        mWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);


    }

    private void initViews() {
        mTabHome = findViewById(R.id.id_tab_home);
        mTabAbout = findViewById(R.id.id_tab_about);
        mTabSetting = findViewById(R.id.id_tab_setting);

        mWeixinImg = findViewById(R.id.id_tab_weixin_img);
        mFrdImg = findViewById(R.id.id_tab_frd_img);
        mAddressImg = findViewById(R.id.id_tab_address_img);
    }

    private void initEvents() {
        mTabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.resetImgs();
                if (mFragHome == null) {
                    mFragHome = new HomeFragment();
                } else {

                }
                setTitle("Home");
                getFragmentManager().beginTransaction().replace(R.id.id_content, mFragHome).commitAllowingStateLoss();
                mWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);
            }
        });

        mTabAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.resetImgs();
                if (mFragAbout == null) {
                    mFragAbout = new AboutFragment();
                } else {

                }
                setTitle("About");
                getFragmentManager().beginTransaction().replace(R.id.id_content, mFragAbout).commitAllowingStateLoss();
                mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
            }
        });

        mTabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.resetImgs();
                if (mFragSetting == null) {
                    mFragSetting = new SettingFragment();
                } else {

                }
                setTitle("Setting");
                getFragmentManager().beginTransaction().replace(R.id.id_content, mFragSetting).commitAllowingStateLoss();
                mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
            }
        });

    }

    private void resetImgs() {
        mWeixinImg.setImageResource(R.mipmap.tab_weixin_normal);
        mFrdImg.setImageResource(R.mipmap.tab_find_frd_normal);
        mAddressImg.setImageResource(R.mipmap.tab_address_normal);
    }

}
