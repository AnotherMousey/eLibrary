package libUser;

public class guest extends libraryUser {
    public guest(String name, String bday, String phone, String email,
                 String username, String password, int uid) {
        super(name, bday, phone, email, username, password, uid, 0);
    }

    public guest() {
        super();
    }

    public guest(libraryUser p) {
        super(p);
    }
}
