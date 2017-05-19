package systems.ellora.core.api.attendance;

import java.sql.Date;

public class Employee {

  private int organizationId;
  private int employeeId;
  private int salary;
  private int department;
  private String employeeName;
  private String designation;
  private Date joingDate;

  public int getEmployeeId() {
    return employeeId;
  }

  public int getOrganizationId() {
    return organizationId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

  public int getDepartment() {
    return department;
  }

  public void setDepartment(int department) {
    this.department = department;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public Date getJoingDate() {
    return joingDate;
  }

  public void setJoingDate(Date joingDate) {
    this.joingDate = joingDate;
  }

  public Employee(int employeeId, int salary, int department, String employeeName,
      String designation, Date joingDate) {
    super();
    this.employeeId = employeeId;
    this.salary = salary;
    this.department = department;
    this.employeeName = employeeName;
    this.designation = designation;
    this.joingDate = joingDate;
  }

  @Override
  public String toString() {
    return "Employee [organizationId=" + organizationId + ", employeeId=" + employeeId + ", salary="
        + salary + ", department=" + department + ", employeeName=" + employeeName
        + ", designation=" + designation + ", joingDate=" + joingDate + "]";
  }

}
