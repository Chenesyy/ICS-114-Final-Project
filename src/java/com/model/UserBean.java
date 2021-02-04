package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.*;

public class UserBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ResultSet user(Connection con, String user, String pass) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public boolean check(Connection con, String user, String pass) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public boolean checkUser(Connection con, String user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public ResultSet role(Connection con, String user, String pass) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public void insert(Connection con, String user, String pass, String role) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO USERS (username, password, role) VALUES (?, ?, ?)");
        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, role);
        ps.executeUpdate();
    }

    public ResultSet compare(Connection con, String user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT USERNAME FROM USERS WHERE username = ?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public ResultSet select(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS");
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public boolean checkYear(Connection con, String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS WHERE RELEASED = ?");
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public boolean checkTitle(Connection con, String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS WHERE UPPER(TITLE) LIKE ?");
        ps.setString(1, "%" + param + "%");
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public boolean checkTtl(Connection con, String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS WHERE UPPER(TITLE) = ?");
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public boolean checkGenre(Connection con, String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS WHERE UPPER(GENRE) = ?");
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public ResultSet search(Connection con, String param, String qry) throws SQLException {
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public ResultSet searchTitle(Connection con, String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SONGS WHERE UPPER(TITLE) LIKE ?");
        ps.setString(1, "%" + param + "%");
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public void delete(Connection con, String param, String qry) throws SQLException {
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, param);
        ps.executeUpdate();
    }
    
    public void update(Connection con, String param1, String param2, String qry) throws SQLException {
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, param1);
        ps.setString(2, param2);
        ps.executeUpdate();
    }
    
    public void insertSong(Connection con, String title, String genre, String year) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO SONGS (TITLE, GENRE, RELEASED) VALUES(?, ?, ?)");
        ps.setString(1, title);
        ps.setString(2, genre);
        ps.setString(3, year);
        ps.executeUpdate();
    }

    public static String encrypt(String strToEncrypt, byte[] key) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); //ENCRYPT_MODE 
            encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return encryptedString;
    }
    
    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/UserDB", "app", "app");
        return con;
    }

}
