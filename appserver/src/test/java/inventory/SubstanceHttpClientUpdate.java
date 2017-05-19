package inventory;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

/*
 * Client Class to check Update URL
 * 
 */
public class SubstanceHttpClientUpdate {

  public static void main(String[] args) throws IOException {
    try {

      HttpClient httpClient = HttpClients.createDefault();

      HttpPost postRequest = new HttpPost(
          "http://ec2-35-154-217-178.ap-south-1.compute.amazonaws.com:8080/Curie/Inventory/Substance/Update/01");

      StringEntity input = new StringEntity(
          "[{'category':'Material','organizationId':1,'subType':[{'color':'White',"
          + "'expiryDate':'May 12, 2025 12:00:00 AM','quantity':4500,'rol':10,'size':2,"
          + "'sizekey':'101-2','type':'Medium'},{'color':'White','expiryDate':'Jul 12,"
          + " 2034 12:00:00 AM','quantity':125,'rol':15,'size':3,'sizekey':'101-3',"
          + "'type':'Large'}],'substanceId':101,'substanceName':'Blanket'}]");

      input.setContentType("application/json");

      postRequest.setEntity(input);

      HttpResponse response = httpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != 201) {
        throw new RuntimeException(
            "Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
      }

      System.out.println(response);

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

}
