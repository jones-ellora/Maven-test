package systems.ellora.core.api.appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class Appointment {

  private int appointmentId;
  private int doctorId;
  private int calendarOrder;
  private String slotId;
  private String type;
  private String reason;
  private String priority;
  private String description;
  private String startTime;
  private String endTime;
  private String created;
  private String referral;
  private String duration;
  private String status;
  private String alert;
  private List<Participant> lisOfParticipants;

  public enum AppointmentStatus {
    CONFIRMED, CANCELLED, POSTPONED
  }

  public Appointment() {
  }

  // Getter and Setter
  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public int getCalendarOrder() {
    return calendarOrder;
  }

  public void setCalendarOrder(int calendarOrder) {
    this.calendarOrder = calendarOrder;
  }

  public String getSlotId() {
    return slotId;
  }

  public void setSlotId(String slotId) {
    this.slotId = slotId;
  }

  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getReferral() {
    return referral;
  }

  public void setReferral(String referral) {
    this.referral = referral;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAlert() {
    return alert;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public List<Participant> getLisOfPartcipants() {
    return lisOfParticipants;
  }

  public void setLisOfPartcipants(List<Participant> lisOfPartcipants) {
    this.lisOfParticipants = lisOfPartcipants;
  }

  public Appointment(int appointmentId, int doctorId, int calendarOrder, String slotId, String type,
      String reason, String priority, String description, String startTime, String endTime,
      String created, String referral, String duration, String status, String alert,
      List<Participant> lisOfParticipants) {
    super();
    this.appointmentId = appointmentId;
    this.doctorId = doctorId;
    this.calendarOrder = calendarOrder;
    this.slotId = slotId;
    this.type = type;
    this.reason = reason;
    this.priority = priority;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.created = created;
    this.referral = referral;
    this.duration = duration;
    this.status = status;
    this.alert = alert;
    this.lisOfParticipants = lisOfParticipants;
  }

  // Database Access Methods

  public List<Calendar> getAll(SqlSession session) {

    List<Calendar> lisOfCalendars = new ArrayList<Calendar>();

    lisOfCalendars = session.selectList("CalendarMapper.selectAll");

    return lisOfCalendars;
  }

  public List<Calendar> getAllByDoctorId(SqlSession session, int doctorId) {

    List<Calendar> lisOfCalendars = new ArrayList<Calendar>();

    lisOfCalendars = session.selectList("CalendarMapper.selectAllByDoctorId", doctorId);

    return lisOfCalendars;
  }

  public List<Calendar> getAllByDoctorIdWithMonth(SqlSession session, int doctorId, String month) {

    List<Calendar> lisOfCalendars = new ArrayList<Calendar>();

    HashMap<String, Object> hashMapParameter = new HashMap<String, Object>();
    hashMapParameter.put("doctorId", doctorId);
    hashMapParameter.put("month", month);

    lisOfCalendars = session.selectList("CalendarMapper.selectAllByDoctorIdWithMonth",
        hashMapParameter);

    return lisOfCalendars;
  }

  public List<Calendar> getAllByDoctorIdWithDates(SqlSession session, int doctorId, String fromDate,
      String toDate) {

    HashMap<String, Object> hashMapParameter = new HashMap<String, Object>();
    hashMapParameter.put("doctorId", doctorId);
    hashMapParameter.put("fromDate", fromDate);
    hashMapParameter.put("toDate", toDate);
    List<Calendar> lisOfCalendars = new ArrayList<Calendar>();

    lisOfCalendars = session.selectList("CalendarMapper.selectAllByDoctorIdWithDates",
        hashMapParameter);

    return lisOfCalendars;
  }

  public List<Calendar> getAllByDoctorIdWithDate(SqlSession session, int doctorId, String date) {

    HashMap<String, Object> hashMapParameter = new HashMap<String, Object>();
    hashMapParameter.put("doctorId", doctorId);
    hashMapParameter.put("date", date);
    List<Calendar> lisOfCalendars = new ArrayList<Calendar>();

    lisOfCalendars = session.selectList("CalendarMapper.selectAllByDoctorIdWithDate",
        hashMapParameter);

    return lisOfCalendars;
  }

  public boolean postponeDate(SqlSession session, int doctorId, int calendarOrder, String fromDate,
      String toDate) {

    HashMap<String, Object> hashMapParameter = new HashMap<String, Object>();
    hashMapParameter.put("doctorId", doctorId);
    hashMapParameter.put("calendarOrder", calendarOrder);
    hashMapParameter.put("fromDate", fromDate);

    List<Appointment> lisOfAppointments = null;
    lisOfAppointments = session.selectList("CalendarMapper.getStartTime", hashMapParameter);

    boolean result = false;

    for (Appointment appointment : lisOfAppointments) {

      String secondPartStartTime = appointment.getStartTime().substring(11, 24);

      startTime = toDate + "T" + secondPartStartTime;

      String secondPartEndTime = appointment.getEndTime().substring(11, 24);

      String endTime = toDate + "T" + secondPartEndTime;

      hashMapParameter.put("startTime", startTime);
      hashMapParameter.put("endTime", endTime);
      hashMapParameter.put("fromDate", fromDate);
      hashMapParameter.put("status", "postponed");
      hashMapParameter.put("alert", "POSTPONED");
      hashMapParameter.put("doctorId", appointment.getDoctorId());
      hashMapParameter.put("calendarOrder", calendarOrder);

      try {

        session.selectList("CalendarMapper.postponeDate", hashMapParameter);
        result = true;

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    return result;

  }

  public boolean postponeAppointment(SqlSession session, int doctorId, int calendarOrder,
      int appointmentId, String toDate, String startTime, String endTime) {
    boolean result = false;

    return result;

  }

}