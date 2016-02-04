/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.derby;

import bean.Good;
import dao.interfaces.MakerDAO;
import bean.Maker;
import db.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Peter
 */
public class MyDerbyMakerDAO implements MakerDAO{

    @Override
    public int addMaker(Maker maker) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Maker getMaker(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Maker> getMaker() throws SQLException {
        
        List<Maker> makers = new ArrayList<Maker>();
        Connection conn = Database.getInstance().getConnection();
        String sql = "SELECT * FROM  MAKER";
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);
        while (results.next()) {
            int id = results.getInt("MKR_ID");
            String mkrName = results.getString("MKR_NAME");
            Maker maker = new Maker(id, mkrName);
            makers.add(maker);
        }
        results.close();
        selectStatement.close();
        return makers;
    }

    @Override
    public int updateMaker(Maker maker) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteMaker(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
