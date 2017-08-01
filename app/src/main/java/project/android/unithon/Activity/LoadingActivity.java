package project.android.unithon.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.android.unithon.Model.AuthToken;
import project.android.unithon.Model.User;
import project.android.unithon.R;
import project.android.unithon.utils.AppUtils;
import project.android.unithon.api.ClientService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private static String TAG = "LoadingActivity";

    @BindView(R.id.input_username)
    EditText mInputUsername;
    @BindView(R.id.submit_username)
    Button mSubmitUsername;

    private ClientService clientService;
    private String authTokenValue;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getSharedPreferences(AppUtils.SETTING_FILE_NAME, MODE_PRIVATE);
        if (pref.contains(AppUtils.TOKEN_KEY_NAME)) {
            String username = getPreferences();
            Toast.makeText(getApplicationContext(), R.string.welcome_user_message + username, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("auth", username);
            startActivity(intent);
        }

        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.submit_username) void onClickSubmitUsername(){
        String username = mInputUsername.getText().toString();
        int id = 1; //??????????
        User user = new User(id, username);

        Call<AuthToken> thumbnailCall = clientService.signupUser(user);
        thumbnailCall.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if(response.isSuccessful()){
                    authTokenValue = response.body().getNickname();
                    savePreferences();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("auth", authTokenValue);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {

            }
        });
    }

    private String getPreferences(){
        return pref.getString(AppUtils.TOKEN_KEY_NAME, "");
    }

    private void savePreferences(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(AppUtils.TOKEN_KEY_NAME, authTokenValue);
        editor.commit();
    }
}
