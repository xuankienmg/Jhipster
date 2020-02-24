package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventCategoriesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventCategoriesDTO.class);
        EventCategoriesDTO eventCategoriesDTO1 = new EventCategoriesDTO();
        eventCategoriesDTO1.setId(1L);
        EventCategoriesDTO eventCategoriesDTO2 = new EventCategoriesDTO();
        assertThat(eventCategoriesDTO1).isNotEqualTo(eventCategoriesDTO2);
        eventCategoriesDTO2.setId(eventCategoriesDTO1.getId());
        assertThat(eventCategoriesDTO1).isEqualTo(eventCategoriesDTO2);
        eventCategoriesDTO2.setId(2L);
        assertThat(eventCategoriesDTO1).isNotEqualTo(eventCategoriesDTO2);
        eventCategoriesDTO1.setId(null);
        assertThat(eventCategoriesDTO1).isNotEqualTo(eventCategoriesDTO2);
    }
}
