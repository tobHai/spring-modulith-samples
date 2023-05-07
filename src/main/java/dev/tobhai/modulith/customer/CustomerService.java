package dev.tobhai.modulith.customer;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  public Collection<Customer> getCustomers() {
    return List.of(new Customer(1, Set.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)),
        new Customer(2, Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)));
  }
}
