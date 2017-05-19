package systems.ellora.core.service.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systems.ellora.core.api.inventory.Substance;

/*
 *  Allow us to invoke query in Mapper XML using session
 *  
 */
public class SubstanceService {

  private static final Logger logger = LoggerFactory.getLogger(SubstanceService.class);

  SqlSession session;

  public SubstanceService(SqlSession substanceSession) {
    this.session = substanceSession;
  }

  public List<Substance> getAll(int organizationId) {

    List<Substance> substances = new ArrayList<>();

    try {

      logger.debug("Getting All the Substances");

      substances = session.selectList("SubstanceMapper.selectSubstance", organizationId);

      logger.debug("Got All the Substances");

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    return substances;
  }

  public Substance getById(int organizationId, int substanceId) {

    Substance substance = null;

    try {

      logger.debug("Getting the substance for Id {}", substanceId);

      Substance substanceArgument = new Substance(organizationId, substanceId, "", "");

      substance = session.selectOne("SubstanceMapper.selectSubstanceById", substanceArgument);

      logger.debug("Got the substance for Id {}", substanceId);

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }

    return substance;
  }
}
