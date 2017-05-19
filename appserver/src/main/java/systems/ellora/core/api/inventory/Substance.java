package systems.ellora.core.api.inventory;

import java.io.Serializable;

/*
 * POJO Class for Substance
 * It contains organizationId,
 * substanceId,category and substancename
 * 
 */
public class Substance implements Serializable {

  /**
   * .
   */
  private static final long serialVersionUID = 1L;

  private int organizationId;
  private int substanceId;
  private String substanceName;
  private String category;

  public Substance() {
  }

  public int getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

  public int getSubstanceId() {
    return substanceId;
  }

  public void setSubstanceId(int substanceId) {
    this.substanceId = substanceId;
  }

  public String getSubstanceName() {
    return substanceName;
  }

  public void setSubstanceName(String substanceName) {
    this.substanceName = substanceName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Substance(int organizationId, int substanceId, String substanceName, String category) {
    this.organizationId = organizationId;
    this.substanceId = substanceId;
    this.substanceName = substanceName;
    this.category = category;
  }

  @Override
  public String toString() {
    return "Substance [organizationId=" + organizationId + ", substanceId=" + substanceId
        + ", substanceName=" + substanceName + ", category=" + category + "]";
  }
}
