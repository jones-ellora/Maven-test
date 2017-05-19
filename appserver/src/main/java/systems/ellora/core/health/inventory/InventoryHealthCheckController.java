package systems.ellora.core.health.inventory;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/*
 *  Controller class which controls healthcheck
 *  for inventory web service and provides 
 *  JSON output about the status of the service
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/status")
public class InventoryHealthCheckController {

  private HealthCheckRegistry registry;

  public InventoryHealthCheckController(HealthCheckRegistry registry) {
    this.registry = registry;
  }

  @GET
  public Set<Entry<String, Result>> getStatus() {
    final Map<String, HealthCheck.Result> resultHashMap = registry.runHealthChecks();

    for (Entry<String, HealthCheck.Result> entry : resultHashMap.entrySet()) {

      if (entry.getValue().isHealthy()) {

        System.out.println(entry.getKey() + " is healthy");

      } else {

        System.err.println(entry.getKey() + " is UNHEALTHY: " + entry.getValue().getMessage());

        final Throwable e = entry.getValue().getError();

        if (e != null) {

          e.printStackTrace();
        }
      }
    }

    Set<Entry<String, Result>> resultSet = resultHashMap.entrySet();

    return resultSet;
  }
}
