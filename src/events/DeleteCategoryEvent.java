/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.List;

/**
 *
 * @author Peter
 */
public class DeleteCategoryEvent {
    private List<Integer> listId;

    public List<Integer> getId() {
        return listId;
    }

    public void setId(List<Integer> id) {
        this.listId = id;
    }

    public DeleteCategoryEvent(List<Integer> id) {
        this.listId = id;
    }

    public DeleteCategoryEvent() {
    }    
    
}
