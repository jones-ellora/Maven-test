package systems.ellora.core.service.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systems.ellora.core.api.inventory.Size;
import systems.ellora.core.api.inventory.Substance;

/*
 *  Allow us to invoke query in Mapper XML using session
 *  
 */
public class SizeService {

  private static final Logger logger = LoggerFactory.getLogger(SizeService.class);

  SqlSession session;

  public SizeService(SqlSession sizeSession) {
    this.session = sizeSession;
  }

  public List<Size> getAll(int organizationId) {

    List<Size> size = new ArrayList<>();

    try {

      logger.debug("Getting All the Substance's Size");

      size = session.selectList("SizeMapper.selectSize", organizationId);

      logger.debug("Got All the Substance's Size");

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    return size;
  }

  public List<Size> getById(int organizationId, int substanceId) {

    List<Size> size = null;

    try {

      logger.debug("Getting the size for substance with Id {}", substanceId);

      Substance substanceArgument = new Substance(organizationId, substanceId, "", "");

      size = session.selectList("SizeMapper.selectSizeById", substanceArgument);

      logger.debug("Got the size for substance with Id {}", substanceId);

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    return size;
  }

  public void updateSize(int organizationId, int substanceId, String sizekey, int quantity, int rol,
      String color) {

    try {

      logger.debug(
          "Substance with Id {} - SizeKey {} is updating with Quantity - {} , rol - {}, color - {}",
          substanceId, sizekey, quantity, rol, color);

      Size sizeArgument = new Size(organizationId, substanceId, 0, "", sizekey, color, quantity,
          rol, null);

      session.update("SizeMapper.updateSize", sizeArgument);

      logger.debug(
          "Substance with Id {} - SizeKey {} is updated with Quantity - {} , rol - {}, color - {}",
          substanceId, sizekey, quantity, rol, color);

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }
}
