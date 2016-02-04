/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import events.DeleteCategoryEvent;

/**
 *
 * @author Peter
 */
public interface DeleteCategoryListener {
    public void onCategoryDeleted(DeleteCategoryEvent event);
}
