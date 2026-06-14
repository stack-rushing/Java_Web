package com.textbook.web.entity;

public class User {
    private Integer id;
    private String v_name;  // 账号/姓名
    private String v_pass;  // 密码
    private String role;    // 角色：0代表管理员，1代表教师

    // 无参构造方法 (必须有)
    public User() {
    }

    // 全参构造方法
    public User(Integer id, String v_name, String v_pass, String role) {
        this.id = id;
        this.v_name = v_name;
        this.v_pass = v_pass;
        this.role = role;
    }

    // Getter 和 Setter 方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getV_name() { return v_name; }
    public void setV_name(String v_name) { this.v_name = v_name; }

    public String getV_pass() { return v_pass; }
    public void setV_pass(String v_pass) { this.v_pass = v_pass; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", v_name='" + v_name + '\'' + ", role='" + role + '\'' + '}';
    }
}