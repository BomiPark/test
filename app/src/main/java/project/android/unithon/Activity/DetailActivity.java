package project.android.unithon.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import project.android.unithon.Adapter.DetailAdapter;
import project.android.unithon.Adapter.ItemAdapter;
import project.android.unithon.Model.Item;
import project.android.unithon.Model.LatXLngY;
import project.android.unithon.R;
import project.android.unithon.Service.LatticeChangeService;

import static project.android.unithon.R.id.toSearch;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    StaggeredGridLayoutManager st;
    ArrayList<Item> items;

    EditText editSearch;
    Geocoder geoCoder;

    LatLng latLng;
    LatXLngY latXLngY;

    ImageView toCo, toHome, toMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
        setToolbar();
    }

    void init(){

        items = new ArrayList<Item>();
        setData();

        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        st = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(st);
        adapter = new DetailAdapter(items, getApplicationContext(), R.layout.detail_item_layout);
        recyclerView.setAdapter(adapter);

        geoCoder = new Geocoder(this);

        toCo = (ImageView)findViewById(R.id.toCommunity);
        toHome = (ImageView)findViewById(R.id.toHome);
        toMap = (ImageView)findViewById(R.id.toMap);


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
                    Intent intent = new Intent(getApplication(), MapActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    void setData(){

        items.add(new Item(R.drawable.image1, "와 차 잠긴거 뭐냐 기상청 가뭄이라고 하는게 더 웃김", "32분전", "22k","image1"));
        items.add(new Item(R.drawable.image2, "ㅋㅋㅋㅋㅋㅋㅋ계란 끓는다 끓어 대구 장난아니야 공기 뜨거워서 숨을 못 쉼", "22분전", "77k", "image2"));
        items.add(new Item(R.drawable.image3, "눈에 잠겼습니다 여러분....... 기상청에 전화하니까 눈 오냐고 놀라던데.......제가 더 놀랐습니다", "1시간 전", "112k", "image3"));
        items.add(new Item(R.drawable.image4, "차에서 낮 잠 자고 일어났는데 배 타고 다니고 있네요ㅋㅋㅋㅋㅋㅋㅋ아까 가뭄이라고 물 부족이라고 하는 거 듣고왔는데 바로 홍수", "34분전", "82k", "image4"));
        items.add(new Item(R.drawable.image5, "날씨봐요...물이 얼 정도면 얼마나 추운거야...", "17분전", "45k", "image5"));
        items.add(new Item(R.drawable.image6, "허리케인ㅋㅋㅋㅋㅋㅋㅋㅋㅋ이 정도는 예측해야 하는 거 아니냐 기상청아? 이 것도 틀리네", "32분전", "84k", "image6"));
        items.add(new Item(R.drawable.image6, "기상청에서 폭우 온다고 해서 바로 짐 싸서 여행옴. 이득", "55분전", "77k", "image7"));
        items.add(new Item(R.drawable.image7, "춥지?", "5분전", "5k", "image8"));

    }

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
