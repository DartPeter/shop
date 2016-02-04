/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.derby;

import bean.BIG;
import dao.interfaces.BIGDAO;
import db.Database;
import events.EditGoodDescrEvent;
import events.EditGoodImageEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class MyDerbyBIGDAO implements BIGDAO {

    @Override
    public BIG getBIG(int id) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "select image, good_info from good where good_id = ?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);
        ResultSet results = selectStatement.executeQuery();

        byte imbyts[] = null;
        String resDesc = null;

        if (results.next()) {
            imbyts = results.getBytes("image");

// !!!!! ДОДУМАТЬ            
            Clob aclob = results.getClob("good_info");
            if (aclob != null) {
                resDesc = "";
                Reader ip = aclob.getCharacterStream();
                char[] buffer = new char[10];
                int size;
                try {
                    while ((size = ip.read(buffer)) > 0) {
                        resDesc += new String(buffer);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        BIG big = new BIG(imbyts, resDesc);

        results.close();
        selectStatement.close();

        return big;
    }

    @Override
    public int setImage(EditGoodImageEvent e) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "update good set image=? where good_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        Blob myBlob = conn.createBlob();
        myBlob.setBytes(1, e.getImage());
        ps.setBlob(1, myBlob);
        ps.setInt(2, e.getId());
        int res = ps.executeUpdate();
        ps.close();
        return res;
    }

    @Override
    public int setDescription(EditGoodDescrEvent e) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "update good set good_info=? where good_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
//        Reader reader = new InputStreamReader(new FileInputStream(f), "windows-1251");
//        ps.setCharacterStream(1, reader);
//        ps.setInt(2, goody2.getId());
        Clob myClob = conn.createClob();
        myClob.setString(1, e.getDescr());
        ps.setClob(1, myClob);
        ps.setInt(2, e.getId());
        int res = ps.executeUpdate();
        ps.close();
//        reader.close();
        return res;
    }
}
