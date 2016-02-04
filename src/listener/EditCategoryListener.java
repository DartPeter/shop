/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import events.EditCategoryEvent;

/**
 *
 * @author Peter
 */
public interface EditCategoryListener {
    public void onCategoryEdited(EditCategoryEvent event);
}
