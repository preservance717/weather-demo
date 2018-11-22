package com.example.galle.weather.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.galle.weather.MyApp;
import com.example.galle.weather.R;

public class SettingFragment extends Fragment {
    private EditText etCity;
    private EditText etState;
    private Button btnSubmit;

    private String cityStr;
    private String stateStr;

    private HomeFragment mFragHome;
    private MyApp myApp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        etCity = view.findViewById(R.id.id_city);
        etState = view.findViewById(R.id.id_state);
        btnSubmit = view.findViewById(R.id.id_submit);
        myApp = (MyApp) getActivity().getApplication();

        cityStr = myApp.getCity().substring(0, myApp.getCity().indexOf(".") );
        stateStr = myApp.getState();
        etCity.setText(cityStr);
        etState.setText(stateStr);


        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                cityStr = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {
                cityStr = s + "";
            }
        });
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                stateStr = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {
                stateStr = s + "";
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragHome == null) {
                    mFragHome = new HomeFragment();
                } else {

                }
                myApp.setCity(cityStr);
                myApp.setState(stateStr);
                Log.i("cityStr", myApp.getCity());
                getFragmentManager().beginTransaction().replace(R.id.id_content, mFragHome).commitAllowingStateLoss();
            }
        });

        return view;
    }
}
