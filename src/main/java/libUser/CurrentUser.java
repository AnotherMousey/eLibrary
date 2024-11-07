package libUser;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class CurrentUser {
    static libraryUser currentUser = new libraryUser();

    @NotNull
    public static String login(String username, String password) {
        try {
            currentUser.logIn(username, password);
        } catch (SQLException e) {
            return "Incorrect username or password";
        }
        if(currentUser.getAuthority() == 0) {
            currentUser = new guest(currentUser);
        } else if(currentUser.getAuthority() == 1) {
            currentUser = new user(currentUser);
        } else {
            currentUser = new admin(currentUser);
        }
        return "Login successfully";
    }

    public static String register(String username, String password,
                                  String dob, String email, String phoneNumber) throws SQLException {
        String msg = currentUser.register(username, password, dob, email, phoneNumber);
        currentUser = new libraryUser();
        return msg;
    }

    public static void logout() {
        currentUser = new libraryUser();
    }
}
