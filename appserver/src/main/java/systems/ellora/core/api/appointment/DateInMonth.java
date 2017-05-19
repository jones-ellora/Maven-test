package systems.ellora.core.api.appointment;

import java.util.List;

public class DateInMonth {

  private String date;
  private List<Appointment> lisOfAppointments;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<Appointment> getLisOfAppointments() {
    return lisOfAppointments;
  }

  public void setLisOfAppointments(List<Appointment> lisOfAppointments) {
    this.lisOfAppointments = lisOfAppointments;
  }

  public DateInMonth(String date, List<Appointment> lisOfAppointments) {
    super();
    this.date = date;
    this.lisOfAppointments = lisOfAppointments;
  }

}
