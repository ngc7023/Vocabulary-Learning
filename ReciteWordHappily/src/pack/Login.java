package pack;

public class Login {
    private String LoginName;
    private String LoginPwd;
    private String email;
    private String birthday;
    private String location;
    private String gender;
    private String last_day;
    private String rank;
    private String user_id;

    public String getUser_id(){return user_id;}
    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public String getLoginName() {
        return LoginName;
    }
    public void setLoginName(String loginName) {
        LoginName = loginName;
    }
    public String getLoginPwd() {
        return LoginPwd;
    }
    public void setLoginPwd(String loginPwd) {
        LoginPwd = loginPwd;
    }
    public String getLast_day(){
        return last_day;
    }
    public void setLast_day(String last_day){
        this.last_day=last_day;
    }
    public String getRank(){
        return rank;
    }
    public void setRank(String rank){
        this.rank=rank;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(String birthday){
        this.birthday=birthday;
    }

    public String getLocation(){
        return  location;
    }
    public void setLocation(String location){
        this.location=location;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }


    public Login(String loginName, String loginPwd) {
        super();
        LoginName = loginName;
        LoginPwd = loginPwd;
    }
    public Login(String loginName) {
        super();
        LoginName = loginName;
    }
    public Login() {
        super();
    }
    public Login(String loginName, String loginPwd, String email) {
        super();
        LoginName = loginName;
        LoginPwd = loginPwd;
        this.email = email;
    }
}
