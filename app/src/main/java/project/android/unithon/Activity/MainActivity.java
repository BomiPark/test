package project.android.unithon.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.android.unithon.Fragment.CameraFragment;
import project.android.unithon.Fragment.OpinionFragment;
import project.android.unithon.Fragment.IconSelectFragment;
import project.android.unithon.Fragment.NationalWeatherFragment;
import project.android.unithon.Model.LatXLngY;
import project.android.unithon.R;
import project.android.unithon.Service.LatticeChangeService;
import project.android.unithon.Service.LocationListener;


import static project.android.unithon.R.id.toSearch;

public class MainActivity extends AppCompatActivity implements NationalWeatherFragment.OnFragmentInteractionListener, CameraFragment.OnFragmentInteractionListener
        , IconSelectFragment.OnFragmentInteractionListener, OpinionFragment.OnFragmentInteractionListener {

    private static String TAG = "MainActivity";
    public static final int REQUEST_CAMERA = 1;
    private Boolean isFabOpened = false;
    private String auth;
    EditText editSearch;

    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.fab_call_camera_fragment)
    FloatingActionButton fabCallCameraFragment;
    @BindView(R.id.fab_call_icon_select_fragment)
    FloatingActionButton fabCallIconSelectFragment;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    LocationListener locationListener;

    Button toMap, toList;

    LatXLngY currentPosition;

    LatLng latLng;
    LatXLngY latXLngY;

    Geocoder geoCoder;
    Intent inent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        auth = intent.getStringExtra("auth");

        locationListener = new LocationListener(this);

        currentPosition = new LatXLngY();
        currentPosition = getLatXLngY();

        geoCoder = new Geocoder(this);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        setToolbar();

        toMap = (Button)findViewById(R.id.toMap);
        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        toList = (Button)findViewById(R.id.toList);

        Fragment selectedFragment = null;
        FragmentTransaction deviceSettingFragmentTransaction = getSupportFragmentManager().beginTransaction();
        selectedFragment = IconSelectFragment.newInstance();
        deviceSettingFragmentTransaction.replace(R.id.down_content, selectedFragment);
        deviceSettingFragmentTransaction.commit();



    }

    @OnClick(R.id.fab_main) void onClickFabFloatingButton(){
        Log.d(TAG, "onClickFabFloatingButton() 메인 플로팅 버튼 이벤트 발생");
        animateFabOpening();
    }

    void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        View viewToolbar = getLayoutInflater().inflate(R.layout.tool_bar, null);
        editSearch = (EditText)viewToolbar.findViewById(R.id.search);
        ImageView toSearch = (ImageView) viewToolbar.findViewById(R.id.toSearch);
        toSearch.setOnClickListener(clickListener);

        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    }

    LatXLngY getLatXLngY(){
        LatLng latLng;
        LatXLngY lat;
        latLng = locationListener.getLocation();
        lat = LatticeChangeService.get().convertGRID_GPS(0, latLng.latitude, latLng.longitude);
        return lat;
    }

    @OnClick(R.id.fab_call_camera_fragment) void onClickFabCallCameraFloatingButton(){
        Fragment selectedFragment = null;
        FragmentTransaction deviceSettingFragmentTransaction = getSupportFragmentManager().beginTransaction();
        selectedFragment = CameraFragment.newInstance();
        deviceSettingFragmentTransaction.replace(R.id.down_content, selectedFragment);
        deviceSettingFragmentTransaction.commit();
    }

    @OnClick(R.id.fab_call_icon_select_fragment) void onClickFabCallIconSelectFloatingButton(){
        Fragment selectedFragment = null;
        FragmentTransaction deviceSettingFragmentTransaction = getSupportFragmentManager().beginTransaction();
        selectedFragment = IconSelectFragment.newInstance();
        deviceSettingFragmentTransaction.replace(R.id.down_content, selectedFragment);
        deviceSettingFragmentTransaction.commit();
    }
    ImageView.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case toSearch: //todo 서버에 전송
                    String search = editSearch.getText().toString();
                    //latLng =  getLatLng(search);
                    Intent intent = new Intent(getApplication(), MapActivity.class);
                    startActivity(intent);

                    break;
            }
        }
    };

    public LatLng getLatLng(String address){
        List<Address> list = null;
        try {
            list = geoCoder.getFromLocationName(address, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
    }



    @Override
    public void onFragmentInteraction(Uri uri) { }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void animateFabOpening() {

        if (isFabOpened) {

            fabMain.startAnimation(rotate_backward);
            fabCallCameraFragment.startAnimation(fab_close);
            fabCallIconSelectFragment.startAnimation(fab_close);

            fabCallCameraFragment.setClickable(false);
            fabCallIconSelectFragment.setClickable(false);
            isFabOpened = false;

        } else {

            fabMain.startAnimation(rotate_forward);
            fabCallCameraFragment.startAnimation(fab_open);
            fabCallIconSelectFragment.startAnimation(fab_open);

            fabCallCameraFragment.setClickable(true);
            fabCallIconSelectFragment.setClickable(true);
            isFabOpened = true;
        }
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.toMap :
                inent = new Intent(this, MapActivity.class);
                startActivity(inent);
                break;
            case R.id.toList :
                inent = new Intent(this, ListActivity.class);
                startActivity(inent);
                break;
        }
    }

}