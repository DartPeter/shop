/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.nederby;

import dao.interfaces.MakerDAO;
import bean.Maker;
import java.sql.SQLException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Peter
 */
public class MyNetDerbyMakerDAO implements MakerDAO{

    @Override
    public int addMaker(Maker maker) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Maker getMaker(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Maker> getMaker() throws SQLException {
        List<Maker> lc = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://localhost:8080/ServerZero/admin/maker/list";
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            ObjectMapper objectMapper = new ObjectMapper();
            lc = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Maker>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public int updateMaker(Maker maker) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteMaker(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
