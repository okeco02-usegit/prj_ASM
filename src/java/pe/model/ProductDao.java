/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

public class ProductDao {

    //-----            your code here   --------------------------------
    public List<ProductDto> getProductByName(String search) throws Exception {
        List<ProductDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                // Câu lệnh SQL tìm kiếm sản phẩm theo tên chứa từ khóa
                String sql = "SELECT id, name, category, price, stockQuantity FROM tblProducts WHERE name LIKE ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + search + "%");
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = rs.getDouble("price");
                    int stockQuantity = rs.getInt("stockQuantity");

                    list.add(new ProductDto(id, name, category, price, stockQuantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
