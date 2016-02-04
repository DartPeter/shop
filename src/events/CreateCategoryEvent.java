/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author Peter
 */
public class CreateCategoryEvent {
    private String catName;
    private int parentId;

    public CreateCategoryEvent(String catName, int parentId) {
        this.catName = catName;
        this.parentId = parentId;
    }

    public CreateCategoryEvent() {
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }    
    
}
