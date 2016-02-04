/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import bean.Good;
import bean.Maker;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Peter
 */
public class GoodDialogPanel extends JPanel {

    private JComboBox makers;
    private JTextField model = new JTextField();
    private JTextField price = new JTextField();
    private JCheckBox availability = new JCheckBox("Наличие");
    private List<String> tst = new Vector<String>();

    public GoodDialogPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Производитель"));
        makers = new JComboBox();
        makers.setEditable(false);
        makers.setModel(new DefaultComboBoxModel<String>((Vector<String>) tst));
        add(makers);
        add(new JLabel("Модель"));
        add(model);
        add(new JLabel("Цена"));
        add(price);
        add(availability);
    }

    public GoodDialogPanel(DefaultComboBoxModel dcbm) {
        this();
        makers.setModel(dcbm);
    }

    public GoodDialogPanel(DefaultComboBoxModel dcbm, Good good) {
        this(dcbm);
        int i = 0;
        for (; !(good.getMaker().equals(((Maker) makers.getItemAt(i)).getName())); i++);
        makers.setSelectedIndex(i);
        
        model.setText(good.getModel());
//        System.out.println(Double.toString(good.getPrice()));
//        price.setText(Double.toString(good.getPrice()));

//        DecimalFormat dec = new DecimalFormat("#.00");
//        price.setText(dec.format(good.getPrice()));
//        System.out.println(dec.format(good.getPrice()));

//        price.setText("1234567");
        
//        price.setText(String.valueOf(good.getPrice()));
//        price.setText("" + good.getPrice());
        price.setText("?" + good.getPrice());
        
        availability.setSelected(good.isIsAvailable());
    }

    public JComboBox getMakers() {
        return makers;
    }

    public void setMakers(JComboBox makers) {
        this.makers = makers;
    }

    public JTextField getModel() {
        return model;
    }

    public void setModel(JTextField model) {
        this.model = model;
    }

    public JTextField getPrice() {
        return price;
    }

    public void setPrice(JTextField price) {
        this.price = price;
    }

    public JCheckBox getAvailability() {
        return availability;
    }

    public void setAvailability(JCheckBox availability) {
        this.availability = availability;
    }

    public List<String> getTst() {
        return tst;
    }

    public void setTst(List<String> tst) {
        this.tst = tst;
    }

    public boolean isValid() {
        try {
            Double.parseDouble(price.getText());
        } catch (Exception e) {
            return false;
        }
        return (model.getText() != "" && price.getText() != "");
    }
}
