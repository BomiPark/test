package project.android.unithon.Model;

import java.util.Date;

/*
    @POST ("posts")
    createPost(@Body );

     - 포스트 생성
 */

public class WeatherPostDetail extends WeatherPostResult{

    String prediction_temperature; // 기상청 예측 기온
    String prediction_humidity; // 기상청 예측 습도
    String prediction_rain_prop; // 기상청 강수 확률
    String user_nicknam; // 유저 닉네임
    boolean is_liked; // 좋아요 여부
    Date base_Date; // 기상청 발표 시각
    Date prediction_date; // 예보 시각

    public String getPrediction_temperature() {
        return prediction_temperature;
    }

    public void setPrediction_temperature(String prediction_temperature) {
        this.prediction_temperature = prediction_temperature;
    }

    public String getPrediction_humidity() {
        return prediction_humidity;
    }

    public void setPrediction_humidity(String prediction_humidity) {
        this.prediction_humidity = prediction_humidity;
    }

    public String getPrediction_rain_prop() {
        return prediction_rain_prop;
    }

    public void setPrediction_rain_prop(String prediction_rain_prop) {
        this.prediction_rain_prop = prediction_rain_prop;
    }

    public String getUser_nicknam() {
        return user_nicknam;
    }

    public void setUser_nicknam(String user_nicknam) {
        this.user_nicknam = user_nicknam;
    }

    public boolean is_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public Date getBase_Date() {
        return base_Date;
    }

    public void setBase_Date(Date base_Date) {
        this.base_Date = base_Date;
    }

    public Date getPrediction_date() {
        return prediction_date;
    }

    public void setPrediction_date(Date prediction_date) {
        this.prediction_date = prediction_date;
    }
}
