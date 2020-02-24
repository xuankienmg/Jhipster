package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventLogsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLogs.class);
        EventLogs eventLogs1 = new EventLogs();
        eventLogs1.setId(1L);
        EventLogs eventLogs2 = new EventLogs();
        eventLogs2.setId(eventLogs1.getId());
        assertThat(eventLogs1).isEqualTo(eventLogs2);
        eventLogs2.setId(2L);
        assertThat(eventLogs1).isNotEqualTo(eventLogs2);
        eventLogs1.setId(null);
        assertThat(eventLogs1).isNotEqualTo(eventLogs2);
    }
}
