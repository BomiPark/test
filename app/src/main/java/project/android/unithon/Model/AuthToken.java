package project.android.unithon.Model;

/**
 * Created by USER on 2017-07-29.
 */

public class AuthToken {

    int id;
    String nickname; // 토큰값

    public AuthToken(int id, String nickname){
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
