package systems.ellora.core.health.inventory;

import com.codahale.metrics.health.HealthCheck;

/*
 *  Provides the status of the service
 *   whether it is healthy or not
 */
public class InventoryHealthChecks extends HealthCheck {

  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }
}
