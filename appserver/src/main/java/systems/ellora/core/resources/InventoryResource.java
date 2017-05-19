package systems.ellora.core.resources;

import com.codahale.metrics.annotation.Timed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systems.ellora.core.api.inventory.SubstanceSize;
import systems.ellora.core.service.inventory.SizeService;
import systems.ellora.core.service.inventory.SubstanceService;
import systems.ellora.core.service.inventory.SubstanceSizeMerge;

/**
 * Receives request from the client and return back the response after processing. It returns data
 * from Mysql based on the request and returns back to client in JSON format
 */

@Path("/Curie/Inventory/Substance")

public class InventoryResource {

  private static final Logger logger = LoggerFactory.getLogger(InventoryResource.class);

  private SubstanceService substanceService = null;

  private SizeService sizeService = null;

  public InventoryResource(SubstanceService substanceservice, SizeService sizeservice)
      throws IOException {
    this.substanceService = substanceservice;

    this.sizeService = sizeservice;
  }

  @GET
  @Path("/GetAll/{OrganizationId}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<SubstanceSize> getAll(@PathParam("OrganizationId") Integer organizationId) {
    List<SubstanceSize> result = null;

    try {

      SubstanceSizeMerge substanceSizeMerge = new SubstanceSizeMerge(substanceService, sizeService);

      result = substanceSizeMerge.doMerge(substanceService.getAll(organizationId),
          sizeService.getAll(organizationId));

      logger.info("All Substances Displayed");

    } catch (RuntimeException re) {

      logger.error("getAll ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAll", t.getMessage() + t.getStackTrace());

    }

    return result;
  }

  @GET
  @Path("/GetById/{organizationId}/{substanceId}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public SubstanceSize getById(@PathParam("organizationId") Integer organizationId,
      @PathParam("substanceId") Integer substanceId) {
    SubstanceSize result = null;

    try {
      SubstanceSizeMerge substanceSizeMerge = new SubstanceSizeMerge(substanceService, sizeService);

      result = substanceSizeMerge.doMergeSubstanceSize(
          substanceService.getById(organizationId, substanceId),
          sizeService.getById(organizationId, substanceId));

      logger.info("Substance Displayed with Id {}", substanceId);

    } catch (RuntimeException re) {

      logger.error("getById ", re);

    } catch (Throwable t) {

      logger.error("FATAL getById", t.getMessage() + t.getStackTrace());

    }

    return result;
  }

  @POST
  @Path("/Update/{organizationId}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  public boolean updateSubstance(@PathParam("organizationId") Integer organizationId,
      String updatedSubstance) {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    SubstanceSize[] updatedSubstanceSize = gson.fromJson(updatedSubstance, SubstanceSize[].class);

    List<SubstanceSize> substanceList = new ArrayList<>();
    boolean result = false;

    try {

      logger.info("Substance updated with {}", updatedSubstanceSize.length);

      SubstanceSizeMerge substanceSizeMerge = new SubstanceSizeMerge(substanceService, sizeService);

      substanceList = substanceSizeMerge.updateSubstanceSize(substanceSizeMerge
          .doMerge(substanceService.getAll(organizationId), sizeService.getAll(organizationId)),
          updatedSubstanceSize);

      if (!(substanceList.isEmpty())) {
        result = true;
      }
      logger.info("Number of substances updated {} " + substanceList.size());

    } catch (RuntimeException re) {

      logger.error("updateSubstance ", re);
    } catch (Throwable t) {

      logger.error("FATAL updateSubstance", t.getMessage() + t.getStackTrace());
    }

    return result;
  }
}
