package app;

import controller.Controller;
import javax.swing.SwingUtilities;
import model.Model;
import view.View;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model model = new Model();
                View view = new View(model);
                Controller controller = new Controller(view, model);
                model.setCategoryChangedListener(view);
                model.setGoodChangedListener(view);
                model.setMakerChangedListener(view);
                model.setBigChangedListener(view);
                view.setAppListener(controller);
                view.setCreateCategoryListener(controller);
                view.setDeleteCategoryListener(controller);
                view.setEditCategoryListener(controller);
                view.setSelectCategoryListener(controller);
                view.setCreateGoodListener(controller);
                view.setDeleteGoodListener(controller);
                view.setEditGoodListener(controller);
                view.setSelectGoodListener(controller);
                view.setEditGoodDescrListener(controller);
                view.setEditGoodImageListener(controller);
            };
        });
    }
}
