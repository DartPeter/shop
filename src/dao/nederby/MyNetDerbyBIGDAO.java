/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.nederby;

import bean.BIG;
import dao.interfaces.BIGDAO;
import events.EditGoodDescrEvent;
import events.EditGoodImageEvent;
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
public class MyNetDerbyBIGDAO implements BIGDAO {

    @Override
    public BIG getBIG(int id) throws SQLException {
        BIG resB = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/big/byId" + "?id=" + id;
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            ObjectMapper objectMapper = new ObjectMapper();
            resB = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<BIG>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resB;
    }

    @Override
    public int setImage(EditGoodImageEvent ev) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/big/image/update";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(ev);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("bigjson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public int setDescription(EditGoodDescrEvent ev) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/big/txt/update";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String trans = objectMapper.writeValueAsString(ev);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("bigjson", trans));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
