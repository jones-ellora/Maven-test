package inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*
 *  Client Class to check GetAll URL
 */
public class SubstanceHttpClientGetAll {

  private static final String USER_AGENT = "Mozilla/5.0";

  private static final String GET_URL = "http://ec2-35-154-217-178.ap-south-1.compute.amazonaws.com:8080/Curie/Inventory/Substance/GetAll/01";

  // private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

  public static void main(String[] args) throws IOException {
    sendGet();
    System.out.println("GET DONE");

  }

  private static void sendGet() throws IOException {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    HttpGet httpGet = new HttpGet(GET_URL);

    httpGet.addHeader("User-Agent", USER_AGENT);

    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

    System.out.println("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());

    BufferedReader reader = new BufferedReader(
        new InputStreamReader(httpResponse.getEntity().getContent()));

    String inputLine;

    StringBuffer response = new StringBuffer();

    while ((inputLine = reader.readLine()) != null) {
      response.append(inputLine);
    }

    reader.close();

    System.out.println(response.toString());

    httpClient.close();
  }

}
