package project.android.unithon.Model;

/**
 * Created by USER on 2017-07-29.
 */

public class User {

    int id; // 유저 id
    String nickname; // 유저 닉네임;

    public User(int id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
