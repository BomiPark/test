package project.android.unithon.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.android.unithon.Activity.CommunityActivity;
import project.android.unithon.Model.LatXLngY;
import project.android.unithon.Model.MarkerItem;
import project.android.unithon.R;
import project.android.unithon.Service.LatticeChangeService;
import project.android.unithon.Service.LocationListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment implements GoogleMap.InfoWindowAdapter{

    GoogleMap googleMap;
    static View view;
    static int zoomLevel = 1;

    public static int MIN = 1;
    public static int CENTER = 2;
    public static int MAX = 3;

    LocationListener locationListener;
    MarkerOptions markerOptions;

    ArrayList<MarkerItem> items;

    Marker marker;
    MarkerOptions options;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);}
        catch (InflateException e){}

        locationListener = new LocationListener(getActivity());
        items = new ArrayList<MarkerItem>();

        getData();
        com.google.android.gms.maps.MapFragment mapFragment = (com.google.android.gms.maps.MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapReadyCallback);

        return view;
    }

    OnMapReadyCallback mapReadyCallback  = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap map) {

            googleMap = map;

            LatLng baseLatlng = new LatLng(37.5172360,127.0473250);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baseLatlng, 11));


            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng){ //todo 삭제
                    change(latLng);
                }
            });

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent intent = new Intent(getActivity(), CommunityActivity.class);
                    startActivity(intent);
                    return false;
                }
            });

            googleMap.setMinZoomPreference(6.8f); // 최소 축소는 여기까지

            googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {
                    float zoom = googleMap.getCameraPosition().zoom;
                    if(zoom < 7.0 && zoomLevel != 1){
                        zoomLevel = 1; //todo 서버에 정보 요청
                    }
                    else if(zoom < 11.3 && zoom > 7.95 && zoomLevel != 2){
                        zoomLevel = 2;
                    }
                    else if(zoom > 11.3 && zoomLevel != 3){
                        zoomLevel = 3;
                    }
                }
            });

            for (MarkerItem item : items) {
                options.title(item.getAddress());
                options.position(new LatLng(item.getLat(), item.getLon()));

                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shape_copy_6));

                switch (item.getReal()){
                    case 0 : marker = googleMap.addMarker(options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shape_copy_6)));
                        break;
                    case 1 : marker = googleMap.addMarker(options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shape_copy_12)));
                        break;
                    case 2 : marker = googleMap.addMarker(options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shape_copy_14)));
                        break;
                    case 3 : marker = googleMap.addMarker(options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shape_copy_22)));
                        break;

                }
            }


        }

    };

    public void change(LatLng latLng){

        LatXLngY lat = LatticeChangeService.get().convertGRID_GPS(0, latLng.latitude, latLng.longitude);
    }

    public void getData(){

        items.add(new MarkerItem("서울시 중랑구",0,2,37.6065600,127.0926520));
        items.add(new MarkerItem("서울시 동대문구",1,2,37.5743680,127.0400190));
        items.add(new MarkerItem("서울시 성동구",2,2,37.5633420,127.0371020));
        items.add(new MarkerItem("서울시 광진구",3,2,37.5384840,127.0822940));
        items.add(new MarkerItem("서울시 송파구",0,2,37.5145440,127.1065970));
        items.add(new MarkerItem("서울시 강남구",1,2,37.5172360,127.0473250));
        items.add(new MarkerItem("서울시 서초구",2,2,37.4837120,127.0324110));
        items.add(new MarkerItem("서울시 관악구",3,2,37.4784060,126.9516130));

        options = new MarkerOptions();

    }

    public void setMarker(){
        //markerWindowView = getActivity().getLayoutInflater().inflate(R.layout.marker_window, null); // 마커윈도우
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(view != null){
            ViewGroup parent = (ViewGroup)view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
        }
    }


    @Override
    public View getInfoWindow(Marker marker) { // 먼저 호출 null 이 반환되면 getInfoContent메소드 호출
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) { // 여기서도 null이 반환되면 기본 정보창 반환
        return null;
    }
}
