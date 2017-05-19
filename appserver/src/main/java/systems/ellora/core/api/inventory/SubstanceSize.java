package systems.ellora.core.api.inventory;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/*
 *  Result of the inventory web service 
 *  will be SubstanceSize POJO class
 */
@XmlRootElement
public class SubstanceSize {

  private int organizationId;
  private int substanceId;
  private String substanceName;
  private String category;

  private List<SubType> subType;

  public SubstanceSize() {

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

  public List<SubType> getSubType() {
    return subType;
  }

  public void setSubType(List<SubType> subType) {
    this.subType = subType;
  }

  public SubstanceSize(int organizationId, int substanceId, String substanceName, String category,
      List<SubType> subType) {
    super();
    this.organizationId = organizationId;
    this.substanceId = substanceId;
    this.substanceName = substanceName;
    this.category = category;
    this.subType = subType;
  }

  @Override
  public String toString() {
    return "SubstanceSize [organizationId=" + organizationId + ", substanceId=" + substanceId
        + ", substanceName=" + substanceName + ", category=" + category + ", subType=" + subType
        + "]";
  }
}
