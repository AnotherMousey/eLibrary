package SQLManagement;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class userManagement extends SQL{
    private static Statement stmt;

    /**
     * check if the password is valid.
     * @param password password
     * a password is valid if it contains lowercase, uppercase, and a number, and no special characters.
     * @return true if valid, false otherwise
     */
    public static boolean checkValidPassword(String password) {
        int cntLowerCase = 0, cntUpperCase = 0, cntNumber = 0;
        for (char c : password.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                if (c <= '9') {
                    cntNumber++;
                } else if (c >= 'a') {
                    cntLowerCase++;
                } else {
                    cntUpperCase++;
                }
            } else {
                return false;
            }
        }
        return cntNumber > 0 && cntUpperCase > 0 && cntLowerCase > 0 && password.length() <= 20;
    }

    public static boolean checkUserIfExists(String username) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE username = " + username + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }

    public static void addUser(String username, String password,
                               int priority, String DoB, String email,
                               String phoneNumber) throws SQLException {
        stmt = getStmt();
        String query = "INSERT INTO user (username, password, priority, DoB, email, phoneNumber)"
                + " VALUES ('" + username + "', '" + password + "', " + priority
                + ", '" + DoB + "', '" + email + "', '" + phoneNumber + "');";
        stmt.executeUpdate(query);
    }

    public static int getUserUID(String username, String password) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE username = '" + username
                + "' AND password = '" + password + "';";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (int) result.get(0).get("uid");
    }

    public static String getUserName(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("username");
    }

    public static String getUserPhoneNumber(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("phoneNumber");
    }

    public static String getUserEmail(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("email");
    }

    public static String getUserDoB(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("dob");
    }

    public static int getUserPriority(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (int) result.get(0).get("priority");
    }

    public static void updatePassword(int uid, String newPassword) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET password = '"
                + newPassword + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updatePhoneNumber(int uid, String newPhoneNumber) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET phoneNumber = '"
                + newPhoneNumber + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updateDoB(int uid, String newDoB) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET dob = '"
                + newDoB + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updateName(int uid, String newName) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET name = '"
                + newName + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updateEmail(int uid, String newEmail) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET email = '"
                + newEmail + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updatePriority(int uid, int newPriority) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET priority = '"
                + newPriority + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void deleteUser(int uid) throws SQLException {
        stmt = getStmt();
        String query = "DELETE FROM user WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }
}
