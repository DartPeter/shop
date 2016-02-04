package events;

public class EditCategoryEvent {
    private String name;
    private int id;

    public EditCategoryEvent(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public EditCategoryEvent() {
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
