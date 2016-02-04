/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.nederby;

import dao.interfaces.CategoryDAO;
import bean.Category;
import events.CreateCategoryEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Peter
 */
public class MyNetDerbyCategoryDAO implements CategoryDAO {

    @Override
    public int addCategory(CreateCategoryEvent category) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/category/add";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(category);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("catjson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;            
    }

    @Override
    public Category getCategory(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getCategory() throws SQLException {
        List<Category> lc = null;
        try {
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            HttpGet httpget = new HttpGet("http://localhost:8080/ServerZero/admin/category/testcatlist");
//            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/category/list";
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            ObjectMapper objectMapper = new ObjectMapper();
            lc = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Category>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/category/update";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(category);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("catjson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public int deleteCategory(List<Integer> id) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/category/delete";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(id);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("catjson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
