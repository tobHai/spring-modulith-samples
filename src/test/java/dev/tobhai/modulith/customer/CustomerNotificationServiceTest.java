package dev.tobhai.modulith.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.moments.support.TimeMachine;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.PublishedEvents;
import org.springframework.test.context.TestPropertySource;

@ApplicationModuleTest
@Import(CustomerNotificationServiceTestConfig.class)
@TestPropertySource(properties = "spring.modulith.moments.enable-time-machine=true")
class CustomerNotificationServiceTest {

    private final TimeMachine timeMachine;

    CustomerNotificationServiceTest(final TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    @Test
    void sendNotificationToCustomer(final PublishedEvents publishedEvents) {
        for (var i = 0; i < 7; i++) {
            timeMachine.shiftBy(Duration.ofDays(1));
        }

        assertThat(publishedEvents.ofType(CustomerNotificationEvent.class)
                .matching(e -> e.getCustomerId() == 1))
                .hasSize(2)
                .extracting(CustomerNotificationEvent::getDayOfWeek)
                .containsOnly(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

        assertThat(publishedEvents.ofType(CustomerNotificationEvent.class)
                .matching(e -> e.getCustomerId() == 2))
                .hasSize(2)
                .extracting(CustomerNotificationEvent::getDayOfWeek)
                .containsOnly(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
        ;
    }
}
