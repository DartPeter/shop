/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import bean.Good;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Peter
 */
public interface GoodDAO {

    public int addGood(Good good) throws SQLException;

    public Good getGood(int id) throws SQLException;

    public List<Good> getGoodList(int id) throws SQLException;

    public int updateGood(Good person) throws SQLException;

    public int deleteGood(int id) throws SQLException;

    public int deleteAll() throws SQLException;
}
