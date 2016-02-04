/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.BIG;
import bean.Category;
import bean.Good;
import bean.Maker;
import dialog.CategoryDialogPanel;
import dialog.GoodDialogPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import listener.AppListener;
import listener.CategoryChangedListener;
import events.CreateCategoryEvent;
import listener.CreateCategoryListener;
import events.DeleteCategoryEvent;
import listener.DeleteCategoryListener;
import events.EditCategoryEvent;
import events.EditGoodDescrEvent;
import events.EditGoodImageEvent;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import listener.BIGChangedListener;
import listener.CreateGoodListener;
import listener.DeleteGoodListener;
import listener.EditCategoryListener;
import listener.EditGoodDescrListener;
import listener.EditGoodImageListener;
import listener.EditGoodListener;
import listener.GoodChangedListener;
import listener.MakerChangedListener;
import listener.SelectCategoryListener;
import listener.SelectGoodListener;
import model.Model;

public class View implements CategoryChangedListener, GoodChangedListener, MakerChangedListener, BIGChangedListener {

    private Model model;
    private AppListener appListener;
    private CreateCategoryListener createCategoryListener;
    private DeleteCategoryListener deleteCategoryListener;
    private EditCategoryListener editCategoryListener;
    private SelectCategoryListener selectCategoryListener;
    private CreateGoodListener createGoodListener;
    private DeleteGoodListener deleteGoodListener;
    private EditGoodListener editGoodListener;
    private SelectGoodListener selectGoodListener;
    private EditGoodDescrListener editGoodDescrListener;
    private EditGoodImageListener editGoodImageListener;
    private JPanel mainPanel = new JPanel();
    private JTree jT;
    private JButton addCatButton = new JButton("Add");
    private JButton deleteCatButton = new JButton("Delete");
    private JButton updateCatButton = new JButton("Edit");
    private JList goodsList;
    private JButton addGoodButton = new JButton("Add");
    private JButton deleteGoodButton = new JButton("Delete");
    private JButton updateGoodButton = new JButton("Edit");
    private JLabel labIm = new JLabel();//"здесь была картинка"
    private JTextArea jta = new JTextArea("информация отсутствует");
    private JButton changeIm = new JButton("Ch. image");
    private JButton changeDscr = new JButton("Ch. descr");
//    private DefaultMutableTreeNode rootNode;
    private DefaultListModel<Good> listModel;
    private DefaultComboBoxModel<Maker> cmbxModel;

    public View(Model model) {
        this.model = model;
        mainPanel.setPreferredSize(new Dimension(1000, 555));
        mainPanel.setLayout(new BorderLayout());
        JPanel treePanel = new JPanel(new BorderLayout());
        treePanel.setPreferredSize(new Dimension(333, 555));
//        jT = new JTree(/*(data.getRoot()*/);
//        jT = new JTree(model.getRoot());
//        jT = new JTree(rootNode);
//        jT = new JTree(rootNode);
//        jT.setRootVisible(true);
//        jT = new JTree(new DefaultTreeModel(rootNode));
//        jT = new JTree();



        jT = new JTree(new DefaultTreeModel(model.getRoot()));






//        rootNode = model.getRoot();
//        jT = new JTree(new DefaultTreeModel(rootNode));

        JScrollPane scp = new JScrollPane(jT);
        treePanel.add(scp, BorderLayout.CENTER);
        JPanel buttonCatPanel = new JPanel(new GridLayout(0, 3));
        buttonCatPanel.add(addCatButton);
        buttonCatPanel.add(updateCatButton);
        buttonCatPanel.add(deleteCatButton);
        treePanel.add(buttonCatPanel, BorderLayout.SOUTH);
        mainPanel.add(treePanel, BorderLayout.WEST);
        JPanel goodsListPanel = new JPanel(new BorderLayout());
        goodsListPanel.setPreferredSize(new Dimension(333, 555));
        goodsList = new JList();
        goodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrolPane = new JScrollPane(goodsList);
        goodsListPanel.add(jScrolPane, "Center");
        JPanel buttonGoodPanel = new JPanel(new FlowLayout());
        goodsListPanel.add(buttonGoodPanel, "South");
        buttonGoodPanel.add(addGoodButton);
        buttonGoodPanel.add(updateGoodButton);
        buttonGoodPanel.add(deleteGoodButton);
        JPanel goodsInfoPanel = new JPanel();
        goodsInfoPanel.setPreferredSize(new Dimension(333, 555));
        goodsInfoPanel.setLayout(new BoxLayout(goodsInfoPanel, BoxLayout.Y_AXIS));
        JLabel jltmp = new JLabel("информация о товаре");
        goodsInfoPanel.add(jltmp);

        jta.setLineWrap(true);
        jta.setEditable(false);
        goodsInfoPanel.add(jta);

        mainPanel.add(goodsInfoPanel, BorderLayout.EAST);
        goodsInfoPanel.add(new JLabel("изображение товара"));
        goodsInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel hlpPan1 = new JPanel();
        hlpPan1.setAlignmentX(Component.CENTER_ALIGNMENT);
        hlpPan1.add(labIm);
        hlpPan1.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        goodsInfoPanel.add(hlpPan1);
//        some new buttons
        JPanel butPanel = new JPanel();
        butPanel.add(changeDscr);
        butPanel.add(changeIm);
        goodsInfoPanel.add(butPanel);

        // Frame
        JFrame frame = new JFrame("FileTreeDemo");
        frame.getContentPane().add(mainPanel, "Center");
        frame.setSize(400, 600);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(goodsListPanel, BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                fireOpenEvent();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                fireCloseEvent();
            }
        });

        listModel = new DefaultListModel<Good>();
        cmbxModel = new DefaultComboBoxModel<Maker>(new Vector<Maker>());
        goodsList.setModel(listModel);

        frame.setVisible(true);

        addCatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryDialogPanel cdp = new CategoryDialogPanel();
                int choice = JOptionPane.showConfirmDialog(
                        mainPanel, cdp, "Input Form : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (choice == JOptionPane.OK_OPTION) {
                    String catName = cdp.getCatName();
                    if (catName.length() != 0 && jT.getLastSelectedPathComponent() != null) {
                        Category c = (Category) ((DefaultMutableTreeNode) jT.getLastSelectedPathComponent()).getUserObject();
                        fireCreateCategoryEvent(new CreateCategoryEvent(catName, c.getId()));
                    }
                }
            }
        });

        deleteCatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jT.getLastSelectedPathComponent() != null) {
                    Category c = (Category) ((DefaultMutableTreeNode) jT.getLastSelectedPathComponent()).getUserObject();
                    List<Integer> childs = new LinkedList<Integer>();
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) jT.getLastSelectedPathComponent();
                    Enumeration<DefaultMutableTreeNode> nodes = ((DefaultMutableTreeNode) node).breadthFirstEnumeration();
                    while (nodes.hasMoreElements()) {
                        DefaultMutableTreeNode curNode = nodes.nextElement();
                        childs.add(((Category) curNode.getUserObject()).getId());
                    }
                    fireDeleteCategoryEvent(new DeleteCategoryEvent(childs));
                }
            }
        });

        updateCatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jT.getLastSelectedPathComponent() != null) {
                    Category c = (Category) ((DefaultMutableTreeNode) jT.getLastSelectedPathComponent()).getUserObject();
                    CategoryDialogPanel cdp = new CategoryDialogPanel(c.getName());
                    int choice = JOptionPane.showConfirmDialog(
                            mainPanel, cdp, "Input Form : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (choice == JOptionPane.OK_OPTION) {
                        String catName = cdp.getCatName();
                        if (catName.length() != 0) {
                            fireEditCategoryEvent(new EditCategoryEvent(catName, c.getId()));
                        }
                    }
                }
            }
        });

        jT.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object node = ((JTree) e.getSource()).getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                if (((DefaultMutableTreeNode) node).isLeaf()) {
                    fireSelectCategoryEvent(((Category) ((DefaultMutableTreeNode) node).getUserObject()).getId());
                }
            }
        });

        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jT.getLastSelectedPathComponent() != null
                        && ((DefaultMutableTreeNode) jT.getLastSelectedPathComponent()).isLeaf()) {
//                    System.out.println(cmbxModel);
                    onMakerChanged();
                    GoodDialogPanel gdp = new GoodDialogPanel(cmbxModel);
                    int choice = JOptionPane.showConfirmDialog(
                            mainPanel, gdp, "Input Form : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (choice == JOptionPane.OK_OPTION && gdp.isValid()) {
                        int catid = ((Category) ((DefaultMutableTreeNode) jT.getLastSelectedPathComponent()).getUserObject()).getId();
                        Maker mkr = (Maker) gdp.getMakers().getSelectedItem();
                        String model = gdp.getModel().getText();
                        double price = Double.parseDouble(gdp.getPrice().getText());
                        boolean availability = gdp.getAvailability().isSelected();
                        Good good = new Good(-1, mkr.getId(), mkr.getName(), catid, model, price, availability);
                        fireCreateGoodEvent(good);
                    }
                }
            }
        });

        deleteGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (goodsList.getModel().getSize() > 0 && !goodsList.isSelectionEmpty()) {
                    fireDeleteGoodEvent((Good) goodsList.getSelectedValue());
                }
            }
        });

        updateGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (goodsList.getModel().getSize() > 0 && !goodsList.isSelectionEmpty()) {
                    onMakerChanged();
                    GoodDialogPanel gdp = new GoodDialogPanel(cmbxModel, (Good) goodsList.getSelectedValue());
                    int choice = JOptionPane.showConfirmDialog(
                            mainPanel, gdp, "Input Form : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (choice == JOptionPane.OK_OPTION && gdp.isValid()) {
                        Good good = (Good) goodsList.getSelectedValue();
                        good.setMakerId(((Maker) gdp.getMakers().getSelectedItem()).getId());
                        good.setModel(gdp.getModel().getText());
                        good.setPrice(Double.parseDouble(gdp.getPrice().getText()));
                        good.setIsAvailable(gdp.getAvailability().isSelected());
                        fireEditGoodEvent(good);
                    }
                }
            }
        });

        goodsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (goodsList.getModel().getSize() == 0 || goodsList.isSelectionEmpty()) {
                        return;
                    }
                    fireSelectGoodEvent(((Good) goodsList.getSelectedValue()).getId());
                }
            }
        });

        changeDscr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!goodsList.isSelectionEmpty()) {
                    JFileChooser fc = new JFileChooser("..//ressfordb");
                    int resSel = fc.showOpenDialog(mainPanel);
                    if (resSel == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        int id = ((Good) goodsList.getSelectedValue()).getId();
                        String d = null;
                        try {
                            FileInputStream input = new FileInputStream(f);
                            byte[] fileData = new byte[input.available()];
                            input.read(fileData);
                            input.close();
                            d = new String(fileData, "windows-1251");
                            System.out.println(d);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        jta.setText(d);
                        fireEditGoodDescrEvent(new EditGoodDescrEvent(id, d));
                    }
                }
            }
        });

        changeIm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!goodsList.isSelectionEmpty()) {
                    JFileChooser fc = new JFileChooser("..//ressfordb");
                    int resSel = fc.showOpenDialog(mainPanel);
                    if (resSel == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        int id = ((Good) goodsList.getSelectedValue()).getId();
                        byte[] imba = null;
                        try {
                            FileInputStream input = new FileInputStream(f);
                            imba = new byte[input.available()];
                            input.read(imba);
                            input.close();
                        } catch (Exception ee) {
                            System.out.println(ee.getMessage());
                        }
                        labIm.setIcon(new ImageIcon(imba));
                        fireEditGoodImageEvent(new EditGoodImageEvent(id, imba));
                    }
                }
            }
        });
    }

    private void fireOpenEvent() {
        if (appListener != null) {
            appListener.onOpen();
        }
    }

    private void fireCloseEvent() {
        if (appListener != null) {
            appListener.onClose();
        }
    }

    private void fireCreateCategoryEvent(CreateCategoryEvent event) {
        if (createCategoryListener != null) {
            createCategoryListener.onCategoryCreated(event);
        }
    }

    private void fireDeleteCategoryEvent(DeleteCategoryEvent event) {
        if (deleteCategoryListener != null) {
            deleteCategoryListener.onCategoryDeleted(event);
        }
    }

    private void fireEditCategoryEvent(EditCategoryEvent event) {
        if (editCategoryListener != null) {
            editCategoryListener.onCategoryEdited(event);
        }
    }

    private void fireSelectCategoryEvent(int id) {
        if (selectCategoryListener != null) {
            selectCategoryListener.onCategorySelected(id);
        }
    }

    public void setAppListener(AppListener appListener) {
        this.appListener = appListener;
    }

    @Override
    public void onCategoryChanged() {

        //        listModelTstCat.clear();
        //        List<Category> categories = model.getCategory();
        //        for (Category category : categories) {
        //            listModelTstCat.addElement(category);
        //        }

//        rootNode = null;
//        rootNode = model.getRoot();


//        System.out.println("Root node is " + rootNode);
//        ((DefaultTreeModel)jT.getModel()).setRoot(rootNode);
//        jT.setModel(new DefaultTreeModel(rootNode));
//        System.out.println(jT.getModel().getChildCount(rootNode));



//        jT.updateUI();
//        mainPanel.repaint();
//        jT.revalidate();
//        ((DefaultTreeModel)jT.getModel()).nodeStructureChanged((DefaultMutableTreeNode)jT.getModel().getRoot());

//        jT.setModel(new DefaultTreeModel(model.getRoot()));


//        ((DefaultTreeModel) jT.getModel()).reload();
        ((DefaultTreeModel) jT.getModel()).setRoot(model.getRoot());


    }

    public void setCreateCategoryListener(CreateCategoryListener controller) {
        this.createCategoryListener = controller;
    }

    public void setDeleteCategoryListener(DeleteCategoryListener controller) {
        this.deleteCategoryListener = controller;
    }

    public void setEditCategoryListener(EditCategoryListener controller) {
        this.editCategoryListener = controller;
    }

    public void setSelectCategoryListener(SelectCategoryListener controller) {
        this.selectCategoryListener = controller;
    }

    public void setCreateGoodListener(CreateGoodListener controller) {
        this.createGoodListener = controller;
    }

    public void setDeleteGoodListener(DeleteGoodListener controller) {
        this.deleteGoodListener = controller;
    }

    public void setEditGoodListener(EditGoodListener controller) {
        this.editGoodListener = controller;
    }

    public void setSelectGoodListener(SelectGoodListener controller) {
        this.selectGoodListener = controller;
    }

    public void setEditGoodDescrListener(EditGoodDescrListener controller) {
        this.editGoodDescrListener = controller;
    }

    public void setEditGoodImageListener(EditGoodImageListener controller) {
        this.editGoodImageListener = controller;
    }

    @Override
    public void onGoodChanged() {
        listModel.clear();
        List<Good> goods = model.getGoodFC();
        for (Good good : goods) {
            listModel.addElement(good);
        }
    }

    @Override
    public void onMakerChanged() {
        cmbxModel.removeAllElements();
        try {
            model.loadMakersForDialog();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Vector<Maker> makers = model.getMakersV();
        for (Maker m : makers) {
            cmbxModel.addElement(m);
        }
    }

    private void fireCreateGoodEvent(Good good) {
        if (createGoodListener != null) {
            createGoodListener.onGoodCreated(good);
        }
    }

    private void fireDeleteGoodEvent(Good good) {
        if (deleteGoodListener != null) {
            deleteGoodListener.onGoodDeleted(good);
        }
    }

    private void fireEditGoodEvent(Good good) {
        if (editGoodListener != null) {
            editGoodListener.onGoodEdited(good);
        }
    }

    @Override
    public void onBIGChanged() {
        BIG data = model.getModelBIG();
        jta.setText((data.getDescription() == null) ? "информация отсутствует" : data.getDescription());
        labIm.setIcon((data.getImage() != null) ? (new ImageIcon(data.getImage())) : (new ImageIcon("img_not_available.jpg")));
    }

    public void fireSelectGoodEvent(int id) {
        if (selectGoodListener != null) {
            selectGoodListener.onGoodSelected(id);
        }
    }

    private void fireEditGoodDescrEvent(EditGoodDescrEvent event) {
        if (editGoodDescrListener != null) {
            editGoodDescrListener.onGoodDescrEdited(event);
        }
    }

    private void fireEditGoodImageEvent(EditGoodImageEvent event) {
        if (editGoodImageListener != null) {
            editGoodImageListener.onGoodImageEdited(event);
        }
    }
}