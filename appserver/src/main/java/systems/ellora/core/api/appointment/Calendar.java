package systems.ellora.core.api.appointment;

import java.util.List;

public class Calendar {

  private int calendarId;
  private int calendarOrder;
  private String calendarType;
  private String name;
  private int organizationId;
  private String organizationName;
  private int facilityId;
  private String facilityName;
  private int locationId;
  private String locationName;
  private int doctorId;
  private String doctorName;
  private List<Appointment> lisOfAppointments;

  public Calendar() {

  }

  public int getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(int facilityId) {
    this.facilityId = facilityId;
  }

  public int getCalendarId() {
    return calendarId;
  }

  public void setCalendarId(int calendarId) {
    this.calendarId = calendarId;
  }

  public int getCalendarOrder() {
    return calendarOrder;
  }

  public void setCalendarOrder(int calendarOrder) {
    this.calendarOrder = calendarOrder;
  }

  public String getCalendarType() {
    return calendarType;
  }

  public void setCalendarType(String calendarType) {
    this.calendarType = calendarType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getFacilityName() {
    return facilityName;
  }

  public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
  }

  public int getLocationId() {
    return locationId;
  }

  public void setLocationId(int locationId) {
    this.locationId = locationId;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
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

  public List<Appointment> getLisOfAppointments() {
    return lisOfAppointments;
  }

  public void setLisOfAppointments(List<Appointment> lisOfAppointments) {
    this.lisOfAppointments = lisOfAppointments;
  }

  public Calendar(int calendarId, int calendarOrder, String calendarType, String name,
      int organizationId, String organizationName, int facilityId, String facilityName,
      int locationId, String locationName, int doctorId, String doctorName,
      List<Appointment> lisOfAppointments) {
    super();
    this.calendarId = calendarId;
    this.calendarOrder = calendarOrder;
    this.calendarType = calendarType;
    this.name = name;
    this.organizationId = organizationId;
    this.organizationName = organizationName;
    this.facilityId = facilityId;
    this.facilityName = facilityName;
    this.locationId = locationId;
    this.locationName = locationName;
    this.doctorId = doctorId;
    this.doctorName = doctorName;
    this.lisOfAppointments = lisOfAppointments;
  }

}
