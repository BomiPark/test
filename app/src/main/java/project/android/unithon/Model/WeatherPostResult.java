package project.android.unithon.Model;


import java.util.Date;

/**
 * Created by USER on 2017-07-29.
 */

public class WeatherPostResult extends WeatherPost {

    int id; // 포스트 id
    Date create_at; // 생성시각

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
