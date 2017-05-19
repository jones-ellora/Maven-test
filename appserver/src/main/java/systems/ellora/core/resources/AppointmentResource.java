package systems.ellora.core.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systems.ellora.core.api.appointment.Appointment;
import systems.ellora.core.api.appointment.Calendar;
import systems.ellora.core.api.appointment.CalendarWithDate;
import systems.ellora.core.api.appointment.DateInMonth;
import systems.ellora.core.api.appointment.Participant;
import systems.ellora.core.service.appointment.AppointmentService;

@Path("/curie/calendar/appointment")
public class AppointmentResource {

  private static final Logger logger = LoggerFactory.getLogger(AppointmentResource.class);

  AppointmentService service = null;

  public AppointmentResource(AppointmentService service) {

    this.service = service;
  }

  @GET
  @Path("/getAll")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Calendar> getAll() {

    List<Calendar> listOfCalendar = new ArrayList<>();

    try {

      listOfCalendar = service.getAll();

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendar;
  }

  @GET
  @Path("/getByDoctorID/{doctorID}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Calendar> getAllByDoctorId(@PathParam("doctorID") Integer doctorId) {

    List<Calendar> listOfCalendar = new ArrayList<>();

    try {

      listOfCalendar = service.getAllByDoctorId(doctorId);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendar;
  }

  @GET
  @Path("/getByDoctorIDWithMonth/{doctorID}/{month}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Calendar> getAllByDoctorIdWithMonth(@PathParam("doctorID") Integer doctorId,
      @PathParam("month") String month) {

    List<Calendar> listOfCalendar = new ArrayList<>();

    try {

      listOfCalendar = service.getAllByDoctorIdWithMonth(doctorId, month);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendar;
  }

  @GET
  @Path("/getByDoctorIdWithMonthAndDate/{doctorID}/{month}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Appointment> getByDoctorIdWithMonthAndDate(
      @PathParam("doctorID") Integer doctorId, @PathParam("month") String month) {

    Map<String, Appointment> listOfCalendarWithDates = null;

    try {

      listOfCalendarWithDates = service.getAllByDoctorIdWithMonthAndDate(doctorId, month);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendarWithDates;
  }

  @GET
  @Path("/getByDoctorIDWithDates/{doctorID}/{fromDate}/{toDate}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Calendar> getAllByDoctorIdWithDates(@PathParam("doctorID") Integer doctorId,
      @PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {

    List<Calendar> listOfCalendar = new ArrayList<>();

    try {

      listOfCalendar = service.getAllByDoctorIdWithDates(doctorId, fromDate, toDate);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendar;
  }

  @GET
  @Path("/getByDoctorIDWithDate/{doctorID}/{date}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Calendar> getAllByDoctorIdWithDate(@PathParam("doctorID") Integer doctorId,
      @PathParam("date") String date) {

    List<Calendar> listOfCalendar = new ArrayList<>();

    try {

      listOfCalendar = service.getAllByDoctorIdWithDate(doctorId, date);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return listOfCalendar;
  }

  @GET
  @Path("/postponeDate/{doctorID}/{calendarOrder}/{fromDate}/{toDate}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean postponeDate(@PathParam("doctorID") Integer doctorId,
      @PathParam("calendarOrder") int calendarOrder, @PathParam("fromDate") String fromDate,
      @PathParam("toDate") String toDate) {

    boolean result = false;

    try {

      result = service.postponeDate(doctorId, calendarOrder, fromDate, toDate);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return result;
  }

  @GET
  @Path("/postpone/{doctorID}/{calendarOrder}/{appointmentID}/{toDate}/{startTime}/{endTime}")
  @Timed(absolute = true, name = "requestRuntime")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean postponeAppointment(@PathParam("doctorID") Integer doctorId,
      @PathParam("calendarOrder") int calendarOrder, @PathParam("appointmentID") int appointmentId,
      @PathParam("toDate") String toDate, @PathParam("startTime") String startTime,
      @PathParam("endTime") String endTime) {

    boolean result = false;

    try {

      result = service.postponeAppointment(doctorId, calendarOrder, appointmentId, toDate,
          startTime, endTime);

    } catch (RuntimeException re) {

      logger.error("getAllByDoctorId ", re);

    } catch (Throwable t) {

      logger.error("FATAL getAllByDoctorId", t.getMessage() + t.getStackTrace());

    }

    return result;
  }

  //
  // // @POST
  // // @Path("/book")
  // // @Timed(absolute = true, name = "requestRuntime")
  // // @Produces(MediaType.APPLICATION_JSON)
  // // @Consumes(MediaType.TEXT_PLAIN)
  // // public boolean bookAnAppointment(String appointment) {
  // //
  // // boolean flag = false;
  // //
  // // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  // //
  // // Appointment bookAppointment = gson.fromJson(appointment, Appointment.class);
  // //
  // // try {
  // //
  // // flag = service.bookAnAppointment(bookAppointment);
  // //
  // // } catch (RuntimeException re) {
  // //
  // // logger.error("bookAnAppointment ", re);
  // //
  // // } catch (Throwable t) {
  // //
  // // logger.error("FATAL bookAnAppointment", t.getMessage() + t.getStackTrace());
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // // @GET
  // // @Path("/cancelDay/{doctorId}/{date}")
  // // @Timed(absolute = true, name = "requestRuntime")
  // // @Produces(MediaType.APPLICATION_JSON)
  // // public boolean cancelDay(@PathParam("doctorId") int doctorId,
  // // @PathParam("date") Date date) {
  // //
  // // boolean flag = false;
  // //
  // // try {
  // //
  // // flag = service.cancelDay(doctorId, date);
  // //
  // // } catch (RuntimeException re) {
  // //
  // // logger.error("cancelAppointment ", re);
  // //
  // // } catch (Throwable t) {
  // //
  // // logger.error("FATAL cancelAppointment", t.getMessage() + t.getStackTrace());
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // // @POST
  // // @Path("/cancel")
  // // @Timed(absolute = true, name = "requestRuntime")
  // // @Consumes(MediaType.TEXT_PLAIN)
  // // public boolean cancelAppointment(String appointment) {
  // //
  // // boolean flag = false;
  // //
  // // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  // //
  // // Appointment cancelAppointment = gson.fromJson(appointment, Appointment.class);
  // //
  // // try {
  // //
  // // flag = service.cancelAppointment(cancelAppointment);
  // //
  // // } catch (RuntimeException re) {
  // //
  // // logger.error("cancelAppointment ", re);
  // //
  // // } catch (Throwable t) {
  // //
  // // logger.error("FATAL cancelAppointment", t.getMessage() + t.getStackTrace());
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // @GET
  // @Path("/postponeDay/{doctorId}/{fromDate}/{toDate}")
  // @Timed(absolute = true, name = "requestRuntime")
  // @Produces(MediaType.APPLICATION_JSON)
  // public boolean postponeDay(@PathParam("doctorId") int doctorId,
  // @PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate) {
  //
  // boolean flag = false;
  //
  // try {
  //
  // flag = service.postponeDay(doctorId, fromDate, toDate);
  //
  // } catch (RuntimeException re) {
  //
  // logger.error("postponeAppointment ", re);
  //
  // } catch (Throwable t) {
  //
  // logger.error("FATAL postponeAppointment", t.getMessage() + t.getStackTrace());
  //
  // }
  //
  // return flag;
  // }
  // //
  // // @POST
  // // @Path("/postpone")
  // // @Timed(absolute = true, name = "requestRuntime")
  // // @Produces(MediaType.APPLICATION_JSON)
  // // public boolean postponeAppointment(String appointment) {
  // //
  // // boolean flag = false;
  // //
  // // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  // //
  // // Appointment postponeAppointment = gson.fromJson(appointment, Appointment.class);
  // //
  // // try {
  // //
  // // flag = service.postponeAppointment(postponeAppointment);
  // //
  // // } catch (RuntimeException re) {
  // //
  // // logger.error("postponeAppointment ", re);
  // //
  // // } catch (Throwable t) {
  // //
  // // logger.error("FATAL postponeAppointment", t.getMessage() + t.getStackTrace());
  // //
  // // }
  // //
  // // return flag;
  // // }
  // //
  // //
}
