package web.ssm.po.user;

public class Usermsg {
    private Integer id;

    private String name;

    private Integer age;

    private String idcard;

    private String day;

    private String youslftext;
    
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYouslftext() {
        return youslftext;
    }

    public void setYouslftext(String youslftext) {
        this.youslftext = youslftext == null ? null : youslftext.trim();
    }
}