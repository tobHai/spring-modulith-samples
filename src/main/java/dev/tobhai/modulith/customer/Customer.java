package dev.tobhai.modulith.customer;

import java.time.DayOfWeek;
import java.util.Set;

public record Customer(long id, Set<DayOfWeek> allowedNotificationDays) {}
