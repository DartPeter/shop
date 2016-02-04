/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author Peter
 */
public class EditGoodDescrEvent {
    private int id;
    private String descr;

    public EditGoodDescrEvent() {
    }

    public EditGoodDescrEvent(int id, String descr) {
        this.id = id;
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
