package project.android.unithon.Activity;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import project.android.unithon.Model.LatXLngY;
import project.android.unithon.R;
import project.android.unithon.Service.LatticeChangeService;

import static project.android.unithon.R.id.toSearch;

public class CommunityActivity extends AppCompatActivity {

    EditText editSearch;
    Geocoder geoCoder;

    LatLng latLng;
    LatXLngY latXLngY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        setToolbar();
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

    ImageView.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case toSearch: //todo 서버에 전송

                    String search = editSearch.getText().toString();
                    latLng =  getLatLng(search);
                    latXLngY = LatticeChangeService.get().convertGRID_GPS(0, latLng.latitude, latLng.longitude);
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
}
