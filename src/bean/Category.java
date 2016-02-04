package bean;
//    поля таблицы CATEGORY
//int CAT_ID, varchar CAT_NAME, int PARENT_CAT_ID
//поля класса бина
//catID, catName, parentCatID НО 1ые 2 есть в базовом классе
public class Category {

    private int id;
    private String name;
    private int parentId;

    public Category() {
    }

    public Category(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
