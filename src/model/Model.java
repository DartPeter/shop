/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.BIG;
import bean.Category;
import bean.Good;
import bean.Maker;
import dao.interfaces.CategoryDAO;
import dao.DAOFactory;
import dao.interfaces.BIGDAO;
import dao.interfaces.GoodDAO;
import dao.interfaces.MakerDAO;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.tree.DefaultMutableTreeNode;
import listener.CategoryChangedListener;
import events.CreateCategoryEvent;
import events.DeleteCategoryEvent;
import events.EditCategoryEvent;
import events.EditGoodDescrEvent;
import events.EditGoodImageEvent;
import java.util.Vector;
import listener.BIGChangedListener;
import listener.GoodChangedListener;
import listener.MakerChangedListener;

/**
 *
 * @author Peter
 */
public class Model {

    private List<Category> categories = new LinkedList<Category>();
    private CategoryChangedListener categoryChangedListener;
    private GoodChangedListener goodChangedListener;
    private MakerChangedListener makerChangedListener;
    private BIGChangedListener bigChangedListener;
    
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    
    private List<Good> goodsFC = new LinkedList<Good>();
    private Vector<Maker> makersV = new Vector<Maker>();    
    private BIG modelBIG = new BIG();
    
    private DAOFactory factory = DAOFactory.getFactory(DAOFactory.DERBY);
    private CategoryDAO categoryDAO = factory.getCategoryDAO();
    private GoodDAO goodDAO = factory.getGoodDAO();
    private MakerDAO makerDAO = factory.getMakerDAO();
    private BIGDAO bigDAO = factory.getBIGDAO();

    public void load() throws Exception {
//        DAOFactory factory = DAOFactory.getFactory(DAOFactory.DERBY);
//        DAOFactory factory = DAOFactory.getFactory(DAOFactory.NET_DERBY);
//        CategoryDAO categoryDAO = factory.getCategoryDAO();

        categories.clear();
        categories.addAll(categoryDAO.getCategory());
        
        
        loadTree();

        fireDataChanged();
    }

    final public void loadTree() {
        // read all category table preconstuct tree
//        root = null;
        Queue<Category> catQ = (LinkedList<Category>)categories;//new LinkedList<Category>
        // finding maximum category ID
//        int maxId = -1;
//        for (Category currentCat : catQ) {
//            if (maxId < currentCat.getId()) {
//                maxId = currentCat.getId();
//            }
//        }
        //construct tree
        // find root
        
        root.setUserObject(null);
        
        while (root.getUserObject() == null) {
//        while (root == null) {
            Category curCat = catQ.poll();
            if (curCat.getParentId() == 0) {
                root.setUserObject(curCat);
//                root = new DefaultMutableTreeNode(curCat);
            } else {
                catQ.add(curCat);
            }
        }
        
        root.removeAllChildren();
        
        a:
        while (!catQ.isEmpty()) {
            Category curCat = catQ.poll();
            Category seaCat = null;
            DefaultMutableTreeNode seaNode = null;
            int parentCID = curCat.getParentId();
            Enumeration path = root.breadthFirstEnumeration();
            while (path.hasMoreElements()) {
                seaNode = (DefaultMutableTreeNode) path.nextElement();
                seaCat = (Category) seaNode.getUserObject();
                if (seaCat.getId() == parentCID) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(curCat);
                    //newNode.setParent(seaNode);
                    seaNode.add(newNode);
                    continue a;
                }
            }
            catQ.add(curCat);
        }
    }

    public void setCategoryChangedListener(CategoryChangedListener categoryChangedListener) {
        this.categoryChangedListener = categoryChangedListener;
    }
    
    public void setGoodChangedListener(GoodChangedListener goodChangedListener) {
        this.goodChangedListener = goodChangedListener;
    }
    
    public void setMakerChangedListener(MakerChangedListener makerChangedListener) {
        this.makerChangedListener = makerChangedListener;
    }
    
    public void setBigChangedListener(BIGChangedListener bigChangedListener) {
        this.bigChangedListener = bigChangedListener;
    }

    private void fireDataChanged() {
        if (categoryChangedListener != null) {
            categoryChangedListener.onCategoryChanged();
        }
    }
    
    private void fireDataChangedGood() {
        if (goodChangedListener != null) {
            goodChangedListener.onGoodChanged();
        }
    }

//    public List<Category> getCategory() {
//        return categories;
//    }
    
    public List<Good> getGoodFC() {
        return goodsFC;
    }
    
    public Vector<Maker> getMakersV(){
        return makersV;
    }
    
    public DefaultMutableTreeNode getRoot() {
        return root;
    }
    
    public BIG getModelBIG() {
        return modelBIG;
    }

    public void addCategory(CreateCategoryEvent event) throws Exception{
//        DAOFactory factory = DAOFactory.getFactory(DAOFactory.DERBY);
//        CategoryDAO categoryDAO = factory.getCategoryDAO();
        categoryDAO.addCategory(event);
        load();
    }

    public void deleteCategory(DeleteCategoryEvent event) throws Exception{
//        DAOFactory factory = DAOFactory.getFactory(DAOFactory.DERBY);
//        CategoryDAO categoryDAO = factory.getCategoryDAO();
        categoryDAO.deleteCategory(event.getId());
        load();
    }
    
    public void editCategory(EditCategoryEvent event) throws Exception{
//        DAOFactory factory = DAOFactory.getFactory(DAOFactory.DERBY);
//        CategoryDAO categoryDAO = factory.getCategoryDAO();
        categoryDAO.updateCategory(new Category(event.getId(), event.getName(), -1));
        load();
    }

    public void loadGoodsForCategory(int id) throws Exception {
        goodsFC.clear();
        goodsFC.addAll(goodDAO.getGoodList(id));

        fireDataChangedGood();
    }
    
    public void loadMakersForDialog() throws Exception {
        makersV.clear();
        for(Maker m:makerDAO.getMaker()) {
            makersV.add(m);
        }        
    }

    public void addGood(Good good) throws Exception{
        goodDAO.addGood(good);
        loadGoodsForCategory(good.getCatId());
    }

    public void deleteGood(Good good) throws Exception{
        goodDAO.deleteGood(good.getId());
        loadGoodsForCategory(good.getCatId());
    }

    public void editGood(Good good) throws Exception{
        goodDAO.updateGood(good);
        loadGoodsForCategory(good.getCatId());
    }

//    public void loadBIG() throws Exception{
//        // i dont know what to do here for a while
//        // may be clear data this
//        bigDAO.getBIG(1);
//        //do with them something here        
//    }

    public void loadBIGForGood(int id) throws Exception {
        modelBIG = bigDAO.getBIG(id);
        fireDataChangedBIG();
    }
    
    private void fireDataChangedBIG() {
        if (bigChangedListener != null) {
            bigChangedListener.onBIGChanged();
        }
    }

    public void editGoodDescr(EditGoodDescrEvent e) throws Exception {
        bigDAO.setDescription(e);
        loadDescrForGood(e);
    }

    private void loadDescrForGood(EditGoodDescrEvent e) {
        modelBIG.setDescription(e.getDescr());
    }

    public void editGoodImage(EditGoodImageEvent e) throws Exception {
        bigDAO.setImage(e);
        loadImageForGood(e);
    }

    private void loadImageForGood(EditGoodImageEvent e) {
        modelBIG.setImage(e.getImage());
    }
}
