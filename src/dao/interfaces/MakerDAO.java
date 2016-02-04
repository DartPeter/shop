/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import bean.Maker;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Peter
 */
public interface MakerDAO {

    public int addMaker(Maker maker) throws SQLException;

    public Maker getMaker(int id) throws SQLException;

    public List<Maker> getMaker() throws SQLException;

    public int updateMaker(Maker maker) throws SQLException;

    public int deleteMaker(int id) throws SQLException;
}
