/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.derby;

import dao.interfaces.CategoryDAO;
import bean.Category;
import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import events.CreateCategoryEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class MyDerbyCategoryDAO implements CategoryDAO {
    {
        try {
            Database.getInstance().connect();
        } catch (Exception ex) {
            System.out.println("cant connect");
        }
    }

    private int getMaxCatID() throws SQLException {
        int result;
        Connection conn = Database.getInstance().getConnection();
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("select max(CAT_ID) from CATEGORY");
        rs.next();
        result = rs.getInt(1);
        rs.close();
        stmnt.close();
        return result;
    }

    @Override
    public int addCategory(CreateCategoryEvent categoryEv) throws SQLException {
        int catID = getMaxCatID() + 1;
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn.prepareStatement
                ("insert into CATEGORY (CAT_ID, CAT_NAME, PARENT_CAT_ID) values (?,?,?)");
        p.setInt(1, catID);
        p.setString(2, categoryEv.getCatName());
        p.setInt(3, categoryEv.getParentId());
        int updated = p.executeUpdate();
        p.close();
        return updated;
    }

    @Override
    public Category getCategory(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getCategory() throws SQLException {
        List<Category> catQ = new LinkedList<Category>();
        Connection conn = Database.getInstance().getConnection();
        String sql = "SELECT * from Category";
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);
        while (results.next()) {
            int catId = results.getInt("CAT_ID");
            String catName = results.getString("CAT_NAME");
            int parCatId = results.getInt("PARENT_CAT_ID");
            Category category = new Category(catId, catName, parCatId);
            catQ.add(category);
        }
        results.close();
        selectStatement.close();
        return catQ;
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn
                .prepareStatement("update Category set CAT_NAME=? where CAT_ID=?");
        p.setString(1, category.getName());
        p.setInt(2, category.getId());
        int updated = p.executeUpdate();
        p.close();
        return updated;
    }

    @Override
    public int deleteCategory(List<Integer> id) throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn.prepareStatement("delete from Category where CAT_ID=?");
        int deleted = 0;
        for (int i : id) {
            p.setInt(1, i);
            deleted = p.executeUpdate();
        }
        p.close();
        return deleted;
    }
}
