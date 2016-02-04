package dao;

import dao.interfaces.BIGDAO;
import dao.interfaces.MakerDAO;
import dao.interfaces.GoodDAO;
import dao.interfaces.CategoryDAO;

public abstract class DAOFactory {
	
	public static final int DERBY = 0;
        public static final int NET_DERBY = 1;

	public abstract CategoryDAO getCategoryDAO();
        public abstract GoodDAO getGoodDAO();
        public abstract MakerDAO getMakerDAO();
        public abstract BIGDAO getBIGDAO();
	
	public static DAOFactory getFactory(int type) {
		switch(type) {
		case DERBY:
			return new MyDerbyDAOFactory();
                case NET_DERBY:
                    	return new MyNetDerbyDAOFactory();
		default:
			return null;
		}
	}

}