package libUser;

public class user extends libraryUser{
    public user(String name, String email,
                String username, String password, int uid) {
        super(name, email, username, password, uid, 1);
    }

    public user() {
        super();
    }

    public user(libraryUser p) {
        super(p);
    }
}
