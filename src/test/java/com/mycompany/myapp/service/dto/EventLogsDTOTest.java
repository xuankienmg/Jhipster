package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventLogsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLogsDTO.class);
        EventLogsDTO eventLogsDTO1 = new EventLogsDTO();
        eventLogsDTO1.setId(1L);
        EventLogsDTO eventLogsDTO2 = new EventLogsDTO();
        assertThat(eventLogsDTO1).isNotEqualTo(eventLogsDTO2);
        eventLogsDTO2.setId(eventLogsDTO1.getId());
        assertThat(eventLogsDTO1).isEqualTo(eventLogsDTO2);
        eventLogsDTO2.setId(2L);
        assertThat(eventLogsDTO1).isNotEqualTo(eventLogsDTO2);
        eventLogsDTO1.setId(null);
        assertThat(eventLogsDTO1).isNotEqualTo(eventLogsDTO2);
    }
}
