/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author Peter
 */
public class EditGoodImageEvent {
    private int id;
    private byte[] image;

    public EditGoodImageEvent() {
    }

    public EditGoodImageEvent(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
