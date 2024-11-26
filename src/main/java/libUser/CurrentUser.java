package libUser;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class CurrentUser {
    public static libraryUser currentUser = new libraryUser();

    @NotNull
    public static String login(String username, String password) {
        try {
            currentUser.logIn(username, password);
            if(currentUser.getUid() == -1) {
                return "Incorrect username or password";
            }
        } catch (SQLException e) {
            return "Incorrect username or password";
        }
        if(currentUser.getPriority() == 0) {
            currentUser = new guest(currentUser);
        } else if(currentUser.getPriority() == 1) {
            currentUser = new user(currentUser);
        } else {
            currentUser = new admin(currentUser);
        }
        return "Login successfully";
    }

    public static String register(String username, String password, String email) throws SQLException {
        String msg = currentUser.register(username, password, email);
        currentUser = new libraryUser();
        return msg;
    }

    public static void logout() {
        currentUser = new libraryUser();
    }
}
