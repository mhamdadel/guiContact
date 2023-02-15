/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammed
 */
public class DB {

    String connectUrl = "jdbc:mysql://localhost:3306/addressbook",
            dirverUrl = "com.mysql.cj.jdbc.driver",
            userName = "root",
            password = "";

    private Connection connect() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = DriverManager.getConnection(connectUrl, userName, password);
        return con;
    }

    public Connection con() throws SQLException {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = new MysqlDataSource();

        try {
            fis = new FileInputStream("C:\\Users\\Muhammed\\Desktop\\docs\\java\\JDBC\\javafx\\src\\javafx\\db.properties");
            props.load(fis);
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("USER"));
            mysqlDS.setPassword(props.getProperty("PASSWORD"));
            if (mysqlDS.getConnection() != null) {
                System.out.println("connection done now !");
            }

        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return mysqlDS.getConnection();

    }

    public void batchy() throws SQLException {
        
        Connection con = this.con();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        String SQL = "UPDATE contact SET name = 'Muhammed Adel' WHERE id = 1";
        stmt.addBatch(SQL);
        SQL = "UPDATE contact SET name = 'Muhammed Alaa' WHERE id = 4";
        stmt.addBatch(SQL);
        stmt.executeBatch();
        con.commit();
        con.setAutoCommit( true );
    }

    public ContactPerson getByName(String name) throws SQLException {
        List<ContactPerson> res = new ArrayList<>();

        try (Connection con = connect()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM contact WHERE name = ?");
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                for (int i = 0; rs.next(); i++) {
                    ContactPerson p = new ContactPerson(rs);
                    res.add(p);
                }
            }
            con.close();
        }
        return res.get(0);
    }

    public List<ContactPerson> getAll() throws SQLException {
        List<ContactPerson> res = new ArrayList<>();

        try (Connection con = connect()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM contact");
            for (int i = 0; rs.next(); i++) {
                ContactPerson p = new ContactPerson(rs);
                res.add(p);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public void setByName(String col, String equal, String where) throws SQLException {
        try (Connection con = connect()) {
            PreparedStatement pst = con.prepareStatement("UPDATE contact SET " + col + "= ? WHERE name = ?");
            pst.setString(1, equal);
            pst.setString(2, where);
            System.out.println("row were effected : " + pst.executeUpdate());
            con.close();
        }
    }

    public void deleteByName(String where) throws SQLException {
        try (Connection con = connect()) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM contact WHERE name = ?");
            pst.setString(1, where);
            System.out.println("row were effected : " + pst.executeUpdate());
            con.close();
        }
    }

    public void insertIntoContact(ContactPerson p) throws SQLException {
        try (Connection con = connect()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO `contact` (`id`, `name`, `nick_name`, `address`, `home_phone`, `work_phone`, `cell_phone`, `email`, `birthday`, `web_site`, `profession`) "
                    + "VALUES ('?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?')");
            pst.setNull(1, 0);
            pst.setString(2, p.getName());
            pst.setString(3, p.getNickName());
            pst.setString(4, p.getAddress());
            pst.setString(5, p.getHomePhone());
            pst.setString(6, p.getWorkPhone());
            pst.setString(7, p.getCellPhone());
            pst.setString(8, p.getEmail());
            pst.setString(9, p.getBrithDate());
            //pst.setDate(0, date);
            //setDate
            // Date().valueOf(stringOfDate) ===> to brithDate;
            // p1 : column , p2 object of date
            //java.sql.Date();
            pst.setString(10, p.getWebSite());
            pst.setString(10, p.getProfession());

            System.out.println("row were effected : " + pst.executeUpdate());

            con.close();
        }
    }

}
