package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EventCategoriesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventCategories.class);
        EventCategories eventCategories1 = new EventCategories();
        eventCategories1.setId(1L);
        EventCategories eventCategories2 = new EventCategories();
        eventCategories2.setId(eventCategories1.getId());
        assertThat(eventCategories1).isEqualTo(eventCategories2);
        eventCategories2.setId(2L);
        assertThat(eventCategories1).isNotEqualTo(eventCategories2);
        eventCategories1.setId(null);
        assertThat(eventCategories1).isNotEqualTo(eventCategories2);
    }
}
