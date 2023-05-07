package dev.tobhai.modulith.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.moments.DayHasPassed;
import org.springframework.stereotype.Service;

@Service
public class CustomerNotificationService {

  private final Logger log = LoggerFactory.getLogger(CustomerNotificationService.class);

  private final CustomerService customerService;
  private final ApplicationEventPublisher eventPublisher;

  public CustomerNotificationService(final CustomerService customerService,
      final ApplicationEventPublisher eventPublisher) {
    this.customerService = customerService;
    this.eventPublisher = eventPublisher;
  }

  @EventListener
  public void on(final DayHasPassed dayHasPassed) {
    var passedDate = dayHasPassed.getDate();
    log.info("{} has passed. Checking notifications for customers.", passedDate);
    for (var customer : customerService.getCustomers()) {
      if (customer.allowedNotificationDays().contains(passedDate.getDayOfWeek())) {
        eventPublisher.publishEvent(new CustomerNotificationEvent(this, customer.id(), passedDate.getDayOfWeek()));
      }
    }
  }
}
