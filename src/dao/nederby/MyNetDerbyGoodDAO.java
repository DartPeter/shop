/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.nederby;

import dao.interfaces.GoodDAO;
import bean.Good;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Peter
 */
public class MyNetDerbyGoodDAO implements GoodDAO {

    @Override
    public int addGood(Good person) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/good/add";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(person);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("goojson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public Good getGood(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Good> getGoodList(int id) throws SQLException {
        List<Good> lc = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/good/list" + "?id=" + id;
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            ObjectMapper objectMapper = new ObjectMapper();
            lc = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Good>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public int updateGood(Good person) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/good/add";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(person);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("goojson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public int deleteGood(int id) throws SQLException {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/good/delete";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(id);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("goojson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public int deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
