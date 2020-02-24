package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventTypes.class);
        EventTypes eventTypes1 = new EventTypes();
        eventTypes1.setId(1L);
        EventTypes eventTypes2 = new EventTypes();
        eventTypes2.setId(eventTypes1.getId());
        assertThat(eventTypes1).isEqualTo(eventTypes2);
        eventTypes2.setId(2L);
        assertThat(eventTypes1).isNotEqualTo(eventTypes2);
        eventTypes1.setId(null);
        assertThat(eventTypes1).isNotEqualTo(eventTypes2);
    }
}
