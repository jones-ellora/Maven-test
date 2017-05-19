package systems.ellora.core.api.attendance;

import java.util.List;

public class AttendanceRegister {

  private int employeeId;
  private String employeeName;
  private List<Entry> entries;

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public List<Entry> getEntries() {
    return entries;
  }

  public void setEntries(List<Entry> entries) {
    this.entries = entries;
  }

  public AttendanceRegister(int employeeId, String employeeName, List<Entry> entries) {
    super();
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.entries = entries;
  }

  @Override
  public String toString() {
    return "AttendanceRegister [employeeId=" + employeeId + ", employeeName=" + employeeName + "]";
  }

}
