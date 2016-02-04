/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import bean.BIG;
import bean.Good;
import events.EditGoodDescrEvent;
import java.sql.SQLException;

/**
 *
 * @author Peter
 */
public interface BIGDAO {
    public BIG getBIG(int id) throws SQLException;
    public int setImage(events.EditGoodImageEvent e) throws SQLException;
    public int setDescription(EditGoodDescrEvent e) throws SQLException;
}
