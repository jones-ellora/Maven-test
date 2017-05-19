package systems.ellora.core.api.inventory;

import java.sql.Date;

/*
 *  Result (SubType) of the inventory web service 
 *  will be similar to this POJO class
 *  Single Substance may have multiple Size
 */
public class SubType {

  private int size;
  private int quantity;
  private int rol;
  private String type;
  private String color;
  private String sizekey;

  private Date expiryDate;

  public SubType() {
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

  public String getSizekey() {
    return sizekey;
  }

  public void setSizekey(String sizekey) {
    this.sizekey = sizekey;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public SubType(int size, int quantity, int rol, String type, String color, String sizekey,
      Date expiryDate) {
    super();
    this.size = size;
    this.quantity = quantity;
    this.rol = rol;
    this.type = type;
    this.color = color;
    this.sizekey = sizekey;
    this.expiryDate = expiryDate;
  }

  @Override
  public String toString() {
    return "SubType [size=" + size + ", quantity=" + quantity + ", rol=" + rol + ", type=" + type
        + ", color=" + color + ", sizekey=" + sizekey + ", expiryDate=" + expiryDate + "]";
  }
}
