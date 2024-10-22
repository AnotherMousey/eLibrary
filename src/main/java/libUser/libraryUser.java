package libUser;

public class libraryUser {
    private String name;
    private String bday;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String uid;
    protected int authority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uId) {
        this.uid = uId;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public libraryUser(String name, String bday, String phone, String email, String username, String password, String uid, int authority) {
        this.name = name;
        this.bday = bday;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.authority = authority;
    }

    public libraryUser() {
        name = "";
        bday = "";
        phone = "";
        email = "";
        username = "";
        password = "";
        uid = "0";
        authority = 0;
    }

    public libraryUser(libraryUser p) {
        this.name = p.name;
        this.bday = p.bday;
        this.phone = p.phone;
        this.email = p.email;
        this.username = p.username;
        this.password = p.password;
        this.uid = p.uid;
        this.authority = p.authority;
    }

    public void register(String tenDangNhap, String matKhau) {
        if (checkUserIfExists(tenDangNhap)) {
            System.out.println("User already exists");
            return;
        }

        if (checkValidPassword(matKhau)) {
            System.out.println("Password is not in right format");
            return;
        }

        addUser(tenDangNhap, matKhau);
    }

    public void logIn(String tenDangNhap, String matKhau){
        uid = getUserUID(tenDangNhap, matKhau);
        name = getUserName(uid);
        phone = getUserPhone(uid);
        bday = getUserDoB(uid);
        email = getUserEmail(uid);
        authority = getUserAuthority(uid);
    }

    public void logOut() {
        name = "";
        bday = "";
        phone = "";
        email = "";
        username = "";
        password = "";
        uid = "0";
        authority = 0;
    }

    public void deleteAccount() {
        deleteUser(uid);
        name = "";
        bday = "";
        phone = "";
        email = "";
        username = "";
        password = "";
        uid = "0";
        authority = 0;
    }

    //anh chua hieu cai update nay lem
    public void updateName(String uid, String newPass) {
        updateName();
    }
}
