/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.derby;

import dao.interfaces.GoodDAO;
import bean.Good;
import db.Database;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Peter
 */
public class MyDerbyGoodDAO implements GoodDAO {

    private int getMaxGoodID() throws SQLException {
        int result;
        Connection conn = Database.getInstance().getConnection();
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("select max(GOOD_ID) from GOOD");
        rs.next();
        result = rs.getInt(1);
        rs.close();
        stmnt.close();
        return result;
    }

    @Override
    public int addGood(Good good) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn.prepareStatement("INSERT INTO GOOD (GOOD_ID, MKR_ID, CATEGORY_ID, MODEL_CODE, PRICE, AVAILABLE) "
                + "VALUES (?,?,?,?,?,?)");
        p.setInt(1, getMaxGoodID() + 1);
        p.setInt(2, good.getMakerId());
        p.setInt(3, good.getCatId());
        p.setString(4, good.getModel());
        p.setBigDecimal(5, new BigDecimal(good.getPrice()));
        p.setString(6, good.isIsAvailable() ? "y" : "n");
        int updated = p.executeUpdate();
        p.close();
        return updated;
    }

    @Override
    public Good getGood(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Good> getGoodList(int id) throws SQLException {
        List<Good> goods = new ArrayList<Good>();
        Connection conn = Database.getInstance().getConnection();
        String sql = "SELECT GOOD_ID, GOOD.MKR_ID, MKR_NAME, CATEGORY_ID, MODEL_CODE, PRICE, AVAILABLE "
                + "FROM GOOD, MAKER "
                + "WHERE GOOD.MKR_ID = MAKER.MKR_ID AND CATEGORY_ID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);
        while (results.next()) {
            int idMy = results.getInt("GOOD_ID");
            int mkrId = results.getInt(2);
            String mkrName = results.getString("MKR_NAME");
            int catID = results.getInt("CATEGORY_ID");
            String modCod = results.getString("MODEL_CODE");
            Double price = results.getDouble("PRICE");
            boolean isIs = (results.getString("AVAILABLE").equals("y")) ? true : false;
            Good good = new Good(idMy, mkrId, mkrName, catID, modCod, price, isIs);
            goods.add(good);
        }
        results.close();
        selectStatement.close();
        return goods;
    }

    @Override
    public int updateGood(Good good) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "UPDATE GOOD SET MKR_ID = ?, MODEL_CODE = ?, PRICE = ?, AVAILABLE  = ? "
                + "WHERE GOOD_ID = ?";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setInt(1, good.getMakerId());
        p.setString(2, good.getModel());
        p.setDouble(3, good.getPrice());
        p.setString(4, good.isIsAvailable()?"y":"n");
        p.setInt(5, good.getId());
        int updated = p.executeUpdate();
        p.close();
        return updated;
    }

    @Override
    public int deleteGood(int id) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn.prepareStatement("delete from Good where GOOD_ID=?");
        int deleted = 0;
        p.setInt(1, id);
        deleted = p.executeUpdate();
        p.close();
        return deleted;
    }

    @Override
    public int deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
