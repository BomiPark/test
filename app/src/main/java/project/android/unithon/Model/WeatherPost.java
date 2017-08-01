package project.android.unithon.Model;

import java.util.Date;

/*
    WeatherPostDetail
     -- WeatherPostResult
         -- WeatherPost
 */

public class WeatherPost {

    int prediction_id; // 기상청 예측 id
    int user_id; // 유저 id
    int weather_code; // 날씨 코드
    String text; // 댓글
    double nx;
    double ny;

    public int getPrediction_id() {
        return prediction_id;
    }

    public void setPrediction_id(int prediction_id) {
        this.prediction_id = prediction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(int weather_code) {
        this.weather_code = weather_code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getNx() {
        return nx;
    }

    public void setNx(double nx) {
        this.nx = nx;
    }

    public double getNy() {
        return ny;
    }

    public void setNy(double ny) {
        this.ny = ny;
    }
}
