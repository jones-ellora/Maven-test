package systems.ellora.core;

import com.codahale.metrics.Slf4jReporter;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.LoggerFactory;

import systems.ellora.core.health.inventory.InventoryHealthCheckController;
import systems.ellora.core.health.inventory.InventoryHealthChecks;
import systems.ellora.core.resources.AppointmentResource;
import systems.ellora.core.resources.InventoryResource;
import systems.ellora.core.service.appointment.AppointmentService;
import systems.ellora.core.service.inventory.SizeService;
import systems.ellora.core.service.inventory.SubstanceService;

/*
 *  Allow us to register our resource to dropwizard
 *  which in turn provides RESTFUL Web service.
 *  Uses Data source from C3PO connection pool
 *  Creates Mybatis session and pass as arguments
 *  for both Size and Substance services.  
 */
public class AppServerApplication extends Application<AppServerConfiguration> {

  public static void main(final String[] args) throws Exception {
    new AppServerApplication().run(args);
  }

  @Override
  public String getName() {
    return "AppServer";
  }

  @Override
  public void initialize(final Bootstrap<AppServerConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final AppServerConfiguration configuration, final Environment environment)
      throws IOException {

    // Create Object for Substance service class along with SQL Session and C3PO DataSource Factory

    Reader substanceReader = Resources.getResourceAsReader("Config.xml");

    SqlSessionFactory substanceSqlSessionFactory = new SqlSessionFactoryBuilder()
        .build(substanceReader);

    SqlSession substanceSession = substanceSqlSessionFactory.openSession();

    final SubstanceService substanceService = new SubstanceService(substanceSession);

    // Create Object for Size service class along with SQL Session and C3PO DataSource Factory

    Reader sizeReader = Resources.getResourceAsReader("Config.xml");

    SqlSessionFactory sizeSqlSessionFactory = new SqlSessionFactoryBuilder().build(sizeReader);

    SqlSession sizeSession = sizeSqlSessionFactory.openSession();

    final SizeService sizeService = new SizeService(sizeSession);

    // Create Object fro Appointment service class along with SQL Session and C3PO DataSource
    // Factory

    final AppointmentService appointmentService = new AppointmentService();

    // Some filter for our web service

    FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter",
        CrossOriginFilter.class);

    filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false,
        environment.getApplicationContext().getContextPath() + "*");
    filter.setInitParameter("allowedOrigins", "*");
    filter.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    filter.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
    filter.setInitParameter("allowCredentials", "true");

    // SLF4J Reporter for dropwizard metrics

    final Slf4jReporter reporter = Slf4jReporter.forRegistry(environment.metrics())
        .outputTo(LoggerFactory.getLogger("systems.ellora.core.AppServerApplication"))
        .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
    reporter.start(1, TimeUnit.MINUTES);

    // Registering Inventory Resource and Inventory health checks with dropwizard jersey

    final InventoryResource inventoryResource = new InventoryResource(substanceService,
        sizeService);

    // Registering Appointment Resource

    final AppointmentResource appointmentResource = new AppointmentResource(appointmentService);

    environment.jersey().register(inventoryResource);

    environment.jersey().register(appointmentResource);

    environment.healthChecks().register("InventoryHealthCheck", new InventoryHealthChecks());

    environment.jersey().register(new InventoryHealthCheckController(environment.healthChecks()));
  }
}
