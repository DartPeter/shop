/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.derby.MyDerbyMakerDAO;
import dao.derby.MyDerbyGoodDAO;
import dao.derby.MyDerbyBIGDAO;
import dao.derby.MyDerbyCategoryDAO;
import dao.interfaces.MakerDAO;
import dao.interfaces.GoodDAO;
import dao.interfaces.CategoryDAO;
import bean.Category;
import dao.interfaces.BIGDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Peter
 */
class MyDerbyDAOFactory extends DAOFactory {

    @Override
    public CategoryDAO getCategoryDAO() {
        return new MyDerbyCategoryDAO();
    }

    @Override
    public GoodDAO getGoodDAO() {
        return new MyDerbyGoodDAO();
    }

    @Override
    public MakerDAO getMakerDAO() {
        return new MyDerbyMakerDAO();
    }

    @Override
    public BIGDAO getBIGDAO() {
        return new MyDerbyBIGDAO();
    }
}
