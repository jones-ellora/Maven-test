package systems.ellora.core.api.attendance;

import java.sql.Date;

public class Entry {

  private int entryId;
  private Date todayDate;
  private String inTime;
  private String outTime;
  private String leaveCapture;
  private String reason;

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }

  public Date getTodayDate() {
    return todayDate;
  }

  public void setTodayDate(Date todayDate) {
    this.todayDate = todayDate;
  }

  public String getInTime() {
    return inTime;
  }

  public void setInTime(String inTime) {
    this.inTime = inTime;
  }

  public String getOutTime() {
    return outTime;
  }

  public void setOutTime(String outTime) {
    this.outTime = outTime;
  }

  public String getLeaveCapture() {
    return leaveCapture;
  }

  public void setLeaveCapture(String leaveCapture) {
    this.leaveCapture = leaveCapture;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Entry(int entryId, Date todayDate, String inTime, String outTime, String leaveCapture,
      String reason) {
    super();
    this.entryId = entryId;
    this.todayDate = todayDate;
    this.inTime = inTime;
    this.outTime = outTime;
    this.leaveCapture = leaveCapture;
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "Entry [entryId=" + entryId + ", todayDate=" + todayDate + ", inTime=" + inTime
        + ", outTime=" + outTime + ", leaveCapture=" + leaveCapture + ", reason=" + reason + "]";
  }
}
