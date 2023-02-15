/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import java.util.Date;
import java.sql.*;

/**
 *
 * @author Muhammed
 */
public class ContactPerson {
    private int id;
    private String name;
    private String nickName;
    private String address;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String email;
    private String birthday;
    private String webSite;
    private String profession;

    public ContactPerson (ResultSet rs) throws SQLException{
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.nickName = rs.getString("nick_name");
        this.address = rs.getString("address");
        this.homePhone = rs.getString("home_phone");
        this.workPhone = rs.getString("work_phone");
        this.cellPhone = rs.getString("cell_phone");
        this.birthday = rs.getString("birthday");
        this.webSite = rs.getString("web_site");
        this.profession = rs.getString("profession");
        this.email = rs.getString("email");
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrithDate() {
        return birthday;
    }

    public void setBrithDate(String date) {
        this.birthday = date;
    }

    public String getProfession() {
        return profession;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

