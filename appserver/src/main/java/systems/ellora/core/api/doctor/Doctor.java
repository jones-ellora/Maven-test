package systems.ellora.core.api.doctor;

import java.sql.Date;

public class Doctor {

  private int organizationId;
  private int doctorId;
  private String doctorName;
  private String specialist;
  private Date appointmentDate;
  private String availableTimeFrom;
  private String availableTimeTo;

  public int getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  public String getSpecialist() {
    return specialist;
  }

  public void setSpecialist(String specialist) {
    this.specialist = specialist;
  }

  public Date getToday() {
    return appointmentDate;
  }

  public void setToday(Date today) {
    this.appointmentDate = today;
  }

  public String getAvailableTimeFrom() {
    return availableTimeFrom;
  }

  public void setAvailableTimeFrom(String availableTimeFrom) {
    this.availableTimeFrom = availableTimeFrom;
  }

  public String getAvailableTimeTo() {
    return availableTimeTo;
  }

  public void setAvailableTimeTo(String availableTimeTo) {
    this.availableTimeTo = availableTimeTo;
  }
}
