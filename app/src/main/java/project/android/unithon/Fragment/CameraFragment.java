package project.android.unithon.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import project.android.unithon.R;
import project.android.unithon.Service.Preview;

public class CameraFragment extends Fragment {
    private static String TAG1 = "CameraFragment";
    private OnFragmentInteractionListener mListener;
    public CameraFragment() { }


    private TextureView mCameraTextureView;
    private Preview mPreview;

    Activity mainActivity = getActivity();
    Button button , button2;
    ImageView imageView;
    private static final String TAG = "MAINACTIVITY";

    static final int REQUEST_CAMERA = 1;


    //API 23 이후로는 코드 내에서 퍼미션을 한 번더 물어봐 줘야함  -> 메니페스트에서 한번 물어보고 코드 상에서 총 두번 물어봄 38~50
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(25)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }








    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_camera,container,false);


        mCameraTextureView = (TextureView)v.findViewById(R.id.cameraTextureView);
        button = (Button)v.findViewById(R.id.button1);
        button2 = (Button)v.findViewById(R.id.button2);
        mPreview = new Preview(getContext(), mCameraTextureView);
        imageView = (ImageView)v.findViewById(R.id.imageview);
        if (shouldAskPermissions()) {
            askPermissions();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

     /*           File file = new File("/sdcard/DCIM/pic.jpg");
                file.delete();*/


                BitmapAsyncTask bitmapAsyncTask = new BitmapAsyncTask();
                bitmapAsyncTask.execute();
                // mPreview.takePicture();

               /* try {
                    Thread.sleep(1000);
                    if(file.exists()) {
                        Bitmap bitmapOrg = BitmapFactory.decodeFile("/sdcard/DCIM/pic.jpg");
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmapOrg, 400, 1000, true);
                        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                        imageView.setImageBitmap(rotatedBitmap);
                        file.delete();
                    }
                }
                catch (Exception e) {
                }*/

//               Glide.with(getContext()).load(rotatedBitmap).into(imageView);

            }
        });


        //버튼 2는 지움
/*
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldAskPermissions()) {
                    askPermissions();
                }
                String sdcard = Environment.getExternalStorageState();
                File file = null;
                File imgFile = new File("/sdcard/DCIM/pic.jpg");
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
            }
        });
*/


        return v;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            Context mContext = getActivity();
                            mCameraTextureView = (TextureView)getActivity().findViewById(R.id.cameraTextureView);
                            mPreview = new Preview(mainActivity, mCameraTextureView);
                            mPreview.openCamera();
                            Log.d(TAG,"mPreview set");

                        } else {
                            // finish();
                        }
                    }
                }
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mPreview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPreview.onPause();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public class BitmapAsyncTask extends AsyncTask<String, Integer, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... params) {
            mPreview.takePicture();
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            File file = new File("/sdcard/DCIM/pic.jpg");
            file.delete();
            try {
                Thread.sleep(1000);
                if(file.exists()) {
                    Bitmap bitmapOrg = BitmapFactory.decodeFile("/sdcard/DCIM/pic.jpg");
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmapOrg, 400, 1000, true);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                    imageView.setImageBitmap(rotatedBitmap);
                    file.delete();
                }
            }
            catch (Exception e) {
            }
            super.onPostExecute(bitmap);
        }
    }



}