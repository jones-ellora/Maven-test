package inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.omg.CORBA.portable.InputStream;

/*
 * Client Class to check Insert URL
 * 
 */
public class SubstanceHttpClientInsert {

  static String GET_URL = "http://ec2-35-154-217-178.ap-south-1.compute.amazonaws.com:8080/Curie/Inventory/Substance/Add/01/111/BPChecker/Material";

  // private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

  public static void main(String[] args) throws IOException {
    
    // Request parameters and other properties.
    List<NameValuePair> params = new ArrayList<NameValuePair>(2);

    params.add(new BasicNameValuePair("substanceId", "111"));
    params.add(new BasicNameValuePair("size", "2"));
    params.add(new BasicNameValuePair("type", "Medium"));
    params.add(new BasicNameValuePair("color", "Green"));
    params.add(new BasicNameValuePair("quantity", "10"));
    params.add(new BasicNameValuePair("rol", "3"));
    params.add(new BasicNameValuePair("expiryDate", "2025-06-15"));
    params.add(new BasicNameValuePair("organizationId", "1"));

    HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
    
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    HttpClient httpclient = HttpClients.createDefault();
    
    HttpResponse response = httpclient.execute(httppost);

    HttpEntity entity = response.getEntity();

    if (entity != null) {

      InputStream instream = (InputStream) entity.getContent();

      try {
        // do something useful
      } finally {
        instream.close();
      }
    }

  }

}
