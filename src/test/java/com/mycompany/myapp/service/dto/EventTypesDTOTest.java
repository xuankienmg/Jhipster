package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventTypesDTO.class);
        EventTypesDTO eventTypesDTO1 = new EventTypesDTO();
        eventTypesDTO1.setId(1L);
        EventTypesDTO eventTypesDTO2 = new EventTypesDTO();
        assertThat(eventTypesDTO1).isNotEqualTo(eventTypesDTO2);
        eventTypesDTO2.setId(eventTypesDTO1.getId());
        assertThat(eventTypesDTO1).isEqualTo(eventTypesDTO2);
        eventTypesDTO2.setId(2L);
        assertThat(eventTypesDTO1).isNotEqualTo(eventTypesDTO2);
        eventTypesDTO1.setId(null);
        assertThat(eventTypesDTO1).isNotEqualTo(eventTypesDTO2);
    }
}
