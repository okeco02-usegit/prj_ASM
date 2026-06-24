/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UserDao {
    //-----            your code here   --------------------------------
    public UserDto checkLogin(String userID, String password) throws Exception {
        UserDto user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT fullName FROM tblUsers WHERE userID = ? AND password = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    user = new UserDto(userID, fullName, password, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
}
