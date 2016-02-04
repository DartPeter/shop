/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import events.CreateCategoryEvent;

/**
 *
 * @author Peter
 */
public interface CreateCategoryListener {
    public void onCategoryCreated(CreateCategoryEvent event);
}
