
package systems.ellora.core.service.appointment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systems.ellora.core.api.appointment.Appointment;
import systems.ellora.core.api.appointment.Calendar;
import systems.ellora.core.api.appointment.CalendarWithDate;
import systems.ellora.core.api.appointment.DateInMonth;
import systems.ellora.core.api.appointment.Participant;

public class AppointmentService {

  private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

  Reader reader;

  SqlSessionFactory sessionFactory;

  ComboPooledDataSource dataSource;

  SqlSession session = null;

  public AppointmentService() {

    try {

      reader = Resources.getResourceAsReader("Config.xml");

      sessionFactory = new SqlSessionFactoryBuilder().build(reader);

      session = sessionFactory.openSession();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

  public List<Calendar> formatTimestamp(List<Calendar> listOfCalendars) throws ParseException {

    final String inputFormat = "yyyy-MM-dd HH:mm:ss";
    final String outputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    List<Calendar> listOfResultCalendars = new ArrayList<>();

    List<Appointment> listOfAppointments = new ArrayList<>();

    for (int i = 0; i < listOfCalendars.size(); i++) {

      Calendar calendar = listOfCalendars.get(i);

      for (int j = 0; j < calendar.getLisOfAppointments().size(); j++) {

        Appointment appointment = calendar.getLisOfAppointments().get(j);

        SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));

        String startTime = appointment.getStartTime().toString();

        String resultStartTime = sdf.format(new SimpleDateFormat(inputFormat).parse(startTime));

        String endTime = appointment.getStartTime().toString();

        String resultEndTime = sdf.format(new SimpleDateFormat(inputFormat).parse(endTime));

        String created = appointment.getStartTime().toString();

        String resultCreated = sdf.format(new SimpleDateFormat(inputFormat).parse(created));

        Appointment resultAppointment = new Appointment(appointment.getAppointmentId(),
            appointment.getDoctorId(), appointment.getCalendarOrder(), appointment.getSlotId(),
            appointment.getType(), appointment.getReason(), appointment.getPriority(),
            appointment.getDescription(), resultStartTime, resultEndTime, resultCreated,
            appointment.getReferral(), appointment.getDuration(), appointment.getStatus(),
            appointment.getAlert(), appointment.getLisOfPartcipants());

        listOfAppointments.add(resultAppointment);

      }

      Calendar resultCalendar = new Calendar(calendar.getCalendarId(), calendar.getCalendarOrder(),
          calendar.getCalendarType(), calendar.getName(), calendar.getOrganizationId(),
          calendar.getOrganizationName(), calendar.getFacilityId(), calendar.getFacilityName(),
          calendar.getLocationId(), calendar.getLocationName(), calendar.getDoctorId(),
          calendar.getDoctorName(), listOfAppointments);

      listOfResultCalendars.add(resultCalendar);

    }

    return listOfResultCalendars;

  }

  public List<Calendar> getAll() {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}");

      listOfCalendars = appointment.getAll(session);

      logger.debug("Got appointments With Doctor ID {}");

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return listOfCalendars;
  }

  public List<Calendar> getAllByDoctorId(int doctorId) throws ParseException {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}", doctorId);

      listOfCalendars = formatTimestamp(appointment.getAllByDoctorId(session, doctorId));

      logger.debug("Got appointments With Doctor ID {}", doctorId);

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return listOfCalendars;
  }

  public List<Calendar> getAllByDoctorIdWithMonth(int doctorId, String month) {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}", doctorId);

      listOfCalendars = appointment.getAllByDoctorIdWithMonth(session, doctorId, month);

      logger.debug("Got appointments With Doctor ID {}", doctorId);

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return listOfCalendars;
  }

  public Map<String, Appointment> getAllByDoctorIdWithMonthAndDate(int doctorId,
      String month) {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    List<CalendarWithDate> listOfCalendarWithDates = new ArrayList<CalendarWithDate>();

    List<DateInMonth> listOfDateInMonths = new ArrayList<DateInMonth>();

    List<Appointment> listOfAppointments = new ArrayList<Appointment>();

    String dateFromMonth = null;

    String dateFromAppointment = null;

    Map<String, Appointment> dateWithAppointment = new HashMap<>();

    Map<Integer, Map<String, Appointment>> calendarWithAptDates = new HashMap<>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}", doctorId);

      listOfCalendars = appointment.getAllByDoctorIdWithMonth(session, doctorId, month);

      for (Calendar calendar : listOfCalendars) {

        for (int j = 0; j < calendar.getLisOfAppointments().size(); j++) {

          if (calendar.getLisOfAppointments().get(j).getCalendarOrder() == calendar
              .getCalendarOrder()) {

            Appointment localAppointment = calendar.getLisOfAppointments().get(j);

            dateFromAppointment = calendar.getLisOfAppointments().get(j).getStartTime().substring(8,
                10);

            dateWithAppointment.put(dateFromAppointment, localAppointment);
          }          
        }

      }

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return dateWithAppointment;
  }

  public List<Calendar> getAllByDoctorIdWithDates(int doctorId, String fromDate, String toDate) {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}", doctorId);

      listOfCalendars = appointment.getAllByDoctorIdWithDates(session, doctorId, fromDate, toDate);

      logger.debug("Got appointments With Doctor ID {}", doctorId);

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return listOfCalendars;
  }

  public List<Calendar> getAllByDoctorIdWithDate(int doctorId, String date) {

    List<Calendar> listOfCalendars = new ArrayList<Calendar>();

    try {

      Appointment appointment = new Appointment();

      logger.debug("Getting appointments With Doctor ID {}", doctorId);

      listOfCalendars = appointment.getAllByDoctorIdWithDate(session, doctorId, date);

      logger.debug("Got appointments With Doctor ID {}", doctorId);

    } catch (RuntimeException e) {

      throw new RuntimeException(e);

    }

    return listOfCalendars;
  }

  public boolean postponeDate(int doctorId, int calendarOrder, String fromDate, String toDate) {

    boolean result = false;

    Appointment appointment = new Appointment();

    result = appointment.postponeDate(session, doctorId, calendarOrder, fromDate, toDate);

    return result;
  }

  public boolean postponeAppointment(int doctorId, int calendarOrder, int appointmentId,
      String toDate, String startTime, String endTime) {

    boolean result = false;

    Appointment appointment = new Appointment();

    result = appointment.postponeAppointment(session, doctorId, calendarOrder, appointmentId,
        toDate, startTime, endTime);

    return result;
  }

  //
  // public List<Appointment> getAllByDoctorIdWithDate(int organizationId, int doctorId,
  // Timestamp appointmentDate) {
  //
  // List<Appointment> lisOfAppointments = new ArrayList<Appointment>();
  //
  // try {
  //
  // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  //
  // logger.debug("Getting appointments - Doctor ID {} and Date {} ", doctorId, appointmentDate);
  //
  // lisOfAppointments = appointmentBuilder.getAllByDoctorIdWithDate(organizationId, doctorId,
  // appointmentDate);
  //
  // logger.debug("Got appointments - Doctor ID {} and Date {} ", doctorId, appointmentDate);
  //
  // } catch (RuntimeException e) {
  //
  // throw new RuntimeException(e);
  //
  // }
  //
  // return lisOfAppointments;
  // }
  //

  //
  // // public boolean bookAnAppointment(Appointment bookAppointment) throws IOException {
  // //
  // // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  // //
  // // boolean flag = false;
  // //
  // //// try {
  // ////
  // //// logger.debug("Booking appointment with ID {} , Status {}, Date {} , Time {} ",
  // //// bookAppointment.getId(), bookAppointment.getStatus(), bookAppointment.getCreated(),
  // //// bookAppointment.getStartTime());
  // ////
  // //// if (bookAppointment.ifAvailable(
  // //// bookAppointment.findSlotwithDoctor(bookAppointment.getDoctorId(),
  // //// bookAppointment.getDoctorName(), bookAppointment.getCreated()),
  // //// bookAppointment.getStartTime(), bookAppointment.getEndTime())) {
  // ////
  // //// appointmentBuilder = new AppointmentBuilder(session);
  // ////
  // //// flag = appointmentBuilder.book(bookAppointment);
  // ////
  // //// bookAppointment.confirm().sendNotifications(Appointment.AppointmentStatus.CONFIRMED);
  // ////
  // //// }
  // ////
  // //// logger.debug("Booked appointment with ID {} , Status {}, Date {} , Time {} ",
  // //// bookAppointment.getId(), bookAppointment.getStatus(), bookAppointment.getCreated(),
  // //// bookAppointment.getStartTime());
  // ////
  // //// } catch (RuntimeException e) {
  // ////
  // //// bookAppointment = appointmentBuilder.AppointmentFixingGoneBad();
  // //// throw new RuntimeException(e);
  // ////
  // //// } catch (IOException e) {
  // ////
  // //// e.printStackTrace();
  // //// }
  // ////
  // //// flag = bookAppointment.ifAvailable(
  // //// bookAppointment.findSlotwithDoctor(bookAppointment.getDoctorId(),
  // //// bookAppointment.getDoctorName(), bookAppointment.getCreated()),
  // //// bookAppointment.getStartTime(), bookAppointment.getEndTime());
  // //
  // // return flag;
  // //
  // // }
  // //
  // // public boolean cancelDay(int doctorId, Date date) {
  // //
  // // boolean flag = false;
  // //
  // // try {
  // //
  // // logger.debug("Cancelling appointment with doctor ID {} , Date {}", doctorId, date);
  // //
  // // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  // //
  // // flag = appointmentBuilder.cancelDay(doctorId, date);
  // //
  // // appointmentBuilder.findAppointment().cancel()
  // // .sendNotifications(Appointment.AppointmentStatus.CANCELLED);
  // //
  // // logger.debug("Cancelled appointment with ID {} , Date {}", doctorId, date);
  // //
  // // } catch (RuntimeException e) {
  // //
  // // throw new RuntimeException(e);
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // // public boolean cancelAppointment(Appointment appointment) {
  // //
  // // boolean flag = false;
  // //
  // // try {
  // //
  // // logger.debug("Cancelling appointment with ID {} , Status {}, Date {} , Time {} ",
  // // appointment.getId(), appointment.getStatus(), appointment.getCreated(),
  // // appointment.getStartTime());
  // //
  // // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  // //
  // // flag = appointmentBuilder.cancelAppointment(appointment.getOrganizationId(),
  // // appointment.getId(), appointment.getDoctorId(), appointment.getCreated());
  // //
  // // appointmentBuilder.findAppointment().cancel()
  // // .sendNotifications(Appointment.AppointmentStatus.CANCELLED);
  // //
  // // logger.debug("Cancelled appointment with ID {} , Status {}, Date {} , Time {} ",
  // // appointment.getId(), appointment.getStatus(), appointment.getCreated(),
  // // appointment.getStartTime());
  // //
  // // } catch (RuntimeException e) {
  // //
  // // throw new RuntimeException(e);
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // // public boolean postponeDay(int doctorId, Date fromDate, Date toDate) {
  // //
  // // boolean flag = false;
  // //
  // // try {
  // //
  // // logger.debug("Postponing appointment with doctor ID {} , Date from {} to {}", doctorId,
  // // fromDate, toDate);
  // //
  // // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  // //
  // // flag = appointmentBuilder.postponeDay(doctorId, fromDate, toDate);
  // //
  // // appointmentBuilder.findAppointment().cancel()
  // // .sendNotifications(Appointment.AppointmentStatus.POSTPONED);
  // //
  // // logger.debug("Postponed appointment with doctor ID {} , Date from {} to {}", doctorId,
  // // fromDate, toDate);
  // //
  // // } catch (RuntimeException e) {
  // //
  // // throw new RuntimeException(e);
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // // public boolean postponeAppointment(Appointment appointment) {
  // //
  // // boolean flag = false;
  // //
  // // try {
  // //
  // // logger.debug("Postponing appointment with ID {} , Status {}, Date {} , Time {} ",
  // // appointment.getId(), appointment.getStatus(), appointment.getCreated(),
  // // appointment.getStartTime());
  // //
  // // AppointmentBuilder appointmentBuilder = new AppointmentBuilder(session);
  // //
  // // flag = appointmentBuilder.postponeAppointment(appointment.getOrganizationId(),
  // // appointment.getId(), appointment.getDoctorId(), appointment.getCreated(),
  // // appointment.getStartTime());
  // //
  // // appointmentBuilder.findAppointment().cancel()
  // // .sendNotifications(Appointment.AppointmentStatus.POSTPONED);
  // //
  // // logger.debug("Postponed appointment with ID {} , Status {}, Date {} , Time {} ",
  // // appointment.getId(), appointment.getStatus(), appointment.getCreated(),
  // // appointment.getStartTime());
  // //
  // // } catch (RuntimeException e) {
  // //
  // // throw new RuntimeException(e);
  // //
  // // }
  // //
  // // return flag;
  // //
  // // }
  // //
}
