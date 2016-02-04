/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package z.prepare.load.big;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Peter
 */
public class FillBigData {

    public static void loadTxtToDB() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connDB = DriverManager.getConnection("jdbc:derby://localhost:1527/projtest");
        PreparedStatement ps;
        ps = connDB.prepareStatement("update good set good_info=? where good_id=?");
        File finptxts = new File("..\\ressfordb\\txtsreader.txt");
        BufferedReader br = new BufferedReader(new FileReader(finptxts));
        String rl;
        while ((rl = br.readLine()) != null) {
            System.out.println(rl);
            String[] sa = rl.split("\\s");
            int id = Integer.parseInt(sa[0]);
            String fname = sa[1];
            System.out.println(sa[0] + " " + sa[1]);
            Reader reader = new InputStreamReader(new FileInputStream("..\\ressfordb\\txts\\" + fname), "windows-1251");
            ps.setCharacterStream(1, reader);
            ps.setInt(2, id);
            ps.execute();
            reader.close();
        }
        ps.close();
        connDB.close();
    }
    
    public static void loadImtoDB() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connDB = DriverManager.getConnection("jdbc:derby://localhost:1527/projtest");
        PreparedStatement ps;
        ps = connDB.prepareStatement("update good set image = ? where good_id = ?");
        File finpimgs = new File("..\\ressfordb\\imgsreader.txt");
        BufferedReader br = new BufferedReader(new FileReader(finpimgs));
        String rl;
        while ((rl = br.readLine()) != null) {
            System.out.println(rl);
            String[] sa = rl.split("\\s");
            int id = Integer.parseInt(sa[0]);
            String fname = sa[1];
            System.out.println(sa[0] + " " + sa[1]);
            InputStream fin = new FileInputStream(new File("..\\ressfordb\\pictures\\" + fname));
            ps.setBinaryStream(1, fin);
            ps.setInt(2, id);
            ps.executeUpdate();
            fin.close();
        }
        ps.close();
    }
    
    public static void main(String[] args) throws Exception {
        loadTxtToDB();
        loadImtoDB();
    }
}
