/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Peter
 */
public class CategoryDialogPanel extends JPanel {

    private JTextField catName = new JTextField();

    public CategoryDialogPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Название"));
        add(catName);
    }
    
    public CategoryDialogPanel(String catName) {
        this();
        this.catName.setText(catName);
    }
    
    public String getCatName() {
        return catName.getText();
    }
}
