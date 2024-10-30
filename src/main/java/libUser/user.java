package libUser;

public class user extends libraryUser{
    public user(String name, String bday, String phone, String email,
                String username, String password, int uid) {
        super(name, bday, phone, email, username, password, uid, 1);
    }
}
