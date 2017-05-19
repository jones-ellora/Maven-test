package systems.ellora.core.api.inventory;

import java.sql.Date;

/*
 *  POJO class for Size contains features 
 *  of the substance along with size key.
 *  Size Key can be either 1,2 or 3. 
 *  Each value represents size. (3-Large).
 */
public class Size {

  private int organizationId;
  private int substanceId;
  private int size;
  private int quantity;
  private int rol;
  private String type;
  private String color;
  private String sizekey;
  private Date expiryDate;
  
  public Size(){

  }

  public int getOrganizationId() {
    return organizationId;
  }

  public String getKey() {
    return sizekey;
  }

  public void setKey(String key) {
    this.sizekey = key;
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

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getRol() {
    return rol;
  }

  public void setRol(int rol) {
    this.rol = rol;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  
  /**
   * Assigns value to each properties of Size Class.   * 
   */
  public Size(int organizationId, int substanceId, int size, String type, String key, 
      String color, int quantity, int rol,  Date expiryDate) {
    this.organizationId = organizationId;
    this.substanceId = substanceId;
    this.size = size;
    this.sizekey = key;
    this.quantity = quantity;
    this.rol = rol;
    this.type = type;
    this.color = color;
    this.expiryDate = expiryDate;
  }

  @Override
  public String toString() {
    return "Size [organizationID=" + organizationId + ", substanceID=" + substanceId + ", "
      + "size=" + size + ", quantity=" + quantity + ", rol=" + rol + ", type=" + type  + ", "
       + " , color=" + color + ", expiryDate=" + expiryDate + "]";
  }
}
