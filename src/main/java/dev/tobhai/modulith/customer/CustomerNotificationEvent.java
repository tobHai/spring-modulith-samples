package dev.tobhai.modulith.customer;

import java.time.DayOfWeek;
import org.springframework.context.ApplicationEvent;

public class CustomerNotificationEvent extends ApplicationEvent {

  private long customerId;
  private DayOfWeek dayOfWeek;

  public CustomerNotificationEvent(final Object source, final long customerId, final DayOfWeek dayOfWeek) {
    super(source);
    this.customerId = customerId;
    this.dayOfWeek = dayOfWeek;
  }

  public long getCustomerId() {
    return customerId;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }
}
