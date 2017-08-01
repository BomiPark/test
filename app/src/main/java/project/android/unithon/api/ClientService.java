package project.android.unithon.api;

import java.util.List;

import project.android.unithon.Model.AuthToken;
import project.android.unithon.Model.PredictionModel;
import project.android.unithon.Model.User;
import project.android.unithon.Model.WeatherPost;
import project.android.unithon.Model.WeatherPostDetail;
import project.android.unithon.Model.WeatherPostResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by USER on 2017-07-28.
 */

public interface ClientService {

    @POST("users/signup") // 회원가입
    Call<AuthToken> signupUser(@Body User user);

    @POST("posts") // 포스트 쓰기
    Call<WeatherPostResult> createPost(@Body WeatherPost weatherPost);

    /////////////////////////////////////////// 포스트 쓸 수 없어서 아래 테스트 불가
    @GET("posts?nx={nx}&ny={ny}") // 위치 기반 포스트 리스트 보기
    Call<List<WeatherPostDetail>> getPostList(@Path("nx") double nx, @Path("ny") double ny);

    @GET("posts/{id}") // 포스트 자세히 보기
    Call<WeatherPostDetail> getPostDetail(@Path("id") int user_id);

    @POST("posts") // 포스트 좋아요
    Call<WeatherPostDetail> createPostLike(@Body User user);

    @GET("posts/stat?nx={nx}&ny={ny}") // nx, ny에 존재하는 통계정보
    Call<WeatherPostDetail> getPostStat(@Path("nx") double nx, @Path("ny") double ny);

    @GET("predictions/list?start_nx={start_nx}&start_ny={start_ny}&end_nx={end_nx}&end_ny={end_ny}") // start_nx, start_ny, end_nx, end_ny 이용해서 검색
    Call<List<PredictionModel>> getPredictionList(@Path("start_nx") double start_nx, @Path("start_ny") double start_ny
            , @Path("end_nx") double end_nx, @Path("end_ny") double end_ny);

    @GET("predictions/nx={nx}&ny={ny}")
    Call<Integer> getPrediction(@Path("nx") double nx, @Path("ny") double ny);


}
