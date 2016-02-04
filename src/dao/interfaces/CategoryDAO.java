/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import bean.Category;
import java.sql.SQLException;
import java.util.List;
import events.CreateCategoryEvent;

public interface CategoryDAO {

	public int addCategory(CreateCategoryEvent category) throws SQLException;

	public Category getCategory(int id) throws SQLException;

	public List<Category> getCategory() throws SQLException;

	public int updateCategory(Category category) throws SQLException;

	public int deleteCategory(List<Integer> id) throws SQLException;

}