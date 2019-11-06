package bw.com.retrofit.entity;

/**
 * Time:2019/11/6
 * Author:周盟棋
 * Description:
 */
public class LoginUserEntity {
    public String message;
    public String status;

    public User result;

    public static class User{
        public String phone;
        public String userId;
        public String sessionId;
    }
}
