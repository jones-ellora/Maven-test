package systems.ellora.core.api.appointment;

import java.util.ArrayList;
import java.util.List;

public class AuditBuffer {
  
  private List<AuditEvent> events = null;

  public AuditBuffer() {
    events = new ArrayList<AuditEvent>();
  }

  public void addAuditEvent(AuditEvent event) {
    events.add(event);
  }

  public void flush(){
   //store to DB
  }
  
}
