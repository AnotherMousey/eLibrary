package SQLManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL {
    private static Statement stmt;
    private static final String user = "root";
    private static final String password = "thanhbinh19072006@@";
    //quanhhqt123
    public static Statement getStmt() {
        return stmt;
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
