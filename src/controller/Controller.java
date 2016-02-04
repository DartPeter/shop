/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Good;
import db.Database;
import listener.AppListener;
import events.CreateCategoryEvent;
import listener.CreateCategoryListener;
import events.DeleteCategoryEvent;
import listener.DeleteCategoryListener;
import events.EditCategoryEvent;
import events.EditGoodDescrEvent;
import events.EditGoodImageEvent;
import listener.CreateGoodListener;
import listener.DeleteGoodListener;
import listener.EditCategoryListener;
import listener.EditGoodDescrListener;
import listener.EditGoodImageListener;
import listener.EditGoodListener;
import listener.SelectCategoryListener;
import listener.SelectGoodListener;
import model.Model;
import view.View;

/**
 *
 * @author Peter
 */
public class Controller implements AppListener, CreateCategoryListener, DeleteCategoryListener, 
        EditCategoryListener, SelectCategoryListener, CreateGoodListener, DeleteGoodListener, 
        EditGoodListener, SelectGoodListener, EditGoodDescrListener, EditGoodImageListener {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onOpen() {
        try {
            Database.getInstance().connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            model.load();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println();
        }
    }

    @Override
    public void onClose() {
        Database.getInstance().disconnect();
    }

    @Override
    public void onCategoryCreated(CreateCategoryEvent event) {
        try {
        model.addCategory(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onCategoryDeleted(DeleteCategoryEvent event) {
        try {
        model.deleteCategory(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onCategoryEdited(EditCategoryEvent event) {
        try {
        model.editCategory(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onCategorySelected(int id) {
        try {
        model.loadGoodsForCategory(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onGoodCreated(Good good) {
        try {
        model.addGood(good);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onGoodDeleted(Good good) {
        try {
        model.deleteGood(good);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onGoodEdited(Good good) {
        try {
        model.editGood(good);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onGoodSelected(int id) {
        try {
        model.loadBIGForGood(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onGoodDescrEdited(EditGoodDescrEvent e) {
        try {
        model.editGoodDescr(e);
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
    }

    @Override
    public void onGoodImageEdited(EditGoodImageEvent e) {
        try {
        model.editGoodImage(e);
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
    }
}
