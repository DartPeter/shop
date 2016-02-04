/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.nederby.MyNetDerbyMakerDAO;
import dao.nederby.MyNetDerbyCategoryDAO;
import dao.nederby.MyNetDerbyBIGDAO;
import dao.interfaces.BIGDAO;
import dao.interfaces.MakerDAO;
import dao.interfaces.GoodDAO;
import dao.interfaces.CategoryDAO;
import dao.nederby.MyNetDerbyGoodDAO;

/**
 *
 * @author Peter
 */
public class MyNetDerbyDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() {
        return new MyNetDerbyCategoryDAO();
    }

    @Override
    public GoodDAO getGoodDAO() {
        return new MyNetDerbyGoodDAO();
    }

    @Override
    public MakerDAO getMakerDAO() {
        return new MyNetDerbyMakerDAO();
    }

    @Override
    public BIGDAO getBIGDAO() {
        return new MyNetDerbyBIGDAO();
    }
}
