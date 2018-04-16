package mvc.slice.controller.login;

/**
 * Created by k on 2018/4/10.
 */
public class AccountData {
    private String username;
    private String password;

    public AccountData() {
    }

    public AccountData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
