import java.sql.*;

public class management {
    private static Statement stmt;
    private static final String user = "root";
    private static final String password = "thanhbinh19072006@@";

    /**
     * check if the password is valid.
     * @param password password
     * a password is valid if it contains lowercase, uppercase, and a number, and no special characters.
     * @return true if valid, false otherwise
     */
    public boolean checkValidPassword(String password) {
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

    public boolean checkUserIfExists(String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = " + username + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }

    public void addUser(String username, String password, int priority) throws SQLException {
        String query = "INSERT INTO user (username, password, priority)" +
                " VALUES ('" + username + "', '" + password + "', " + priority + ");";
        stmt.executeUpdate(query);
    }

    public int getUserUID(String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = " + username + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getInt(1);
    }

    public String getUserName(int uid) throws SQLException {
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getString(5);
    }

    public String getUserPhoneNumber(int uid) throws SQLException {
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getString(8);
    }

    public String getUserEmail(int uid) throws SQLException {
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getString(7);
    }

    public String getUserDoB(int uid) throws SQLException {
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getString(6).toString();
    }

    public int getUserPriority(int uid) throws SQLException {
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.getInt(4);
    }

    public void updatePassword(int uid, String newPassword) throws SQLException {
        String query = "UPDATE user SET password = '"
                + newPassword + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public void updatePhoneNumber(int uid, String newPhoneNumber) throws SQLException {
        String query = "UPDATE user SET phoneNumber = '"
                + newPhoneNumber + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public void updateDoB(int uid, String newDoB) throws SQLException {
        String query = "UPDATE user SET dob = '"
                + newDoB + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public void updateName(int uid, String newName) throws SQLException {
        String query = "UPDATE user SET name = '"
                + newName + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public void updateEmail(int uid, String newEmail) throws SQLException {
        String query = "UPDATE user SET email = '"
                + newEmail + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public void updatePriority(int uid, int newPriority) throws SQLException {
        String query = "UPDATE user SET priority = '"
                + newPriority + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", user, password);
            System.out.println("connection success");

            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("connection failed");
        }
    }
}