package libUser;

public class admin extends libraryUser {
    public admin(String name, String email,
                 String username, String password, int uid) {
        super(name, email, username, password, uid, 2);
    }

    public admin() {
        super();
    }

    public admin(libraryUser p) {
        super(p);
    }
}
