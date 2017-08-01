package project.android.unithon.Model;

import java.util.Date;

/**
 * Created by USER on 2017-07-30.
 */

public class PredictionModel {

    int id; // 예보 id
    int code; // 날씨 코드
    String temperature; // 온도
    String humidity; // 습도
    String rainProp; // 강수 확률
    Date base_date; // 기상청 발표 시각
    Date prediction_date; // 기상청 예보 시각
    Date create_at; // 생성시각

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRainProp() {
        return rainProp;
    }

    public void setRainProp(String rainProp) {
        this.rainProp = rainProp;
    }

    public Date getBase_date() {
        return base_date;
    }

    public void setBase_date(Date base_date) {
        this.base_date = base_date;
    }

    public Date getPrediction_date() {
        return prediction_date;
    }

    public void setPrediction_date(Date prediction_date) {
        this.prediction_date = prediction_date;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
