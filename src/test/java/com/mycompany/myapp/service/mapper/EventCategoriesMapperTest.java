package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventCategoriesMapperTest {

    private EventCategoriesMapper eventCategoriesMapper;

    @BeforeEach
    public void setUp() {
        eventCategoriesMapper = new EventCategoriesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventCategoriesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventCategoriesMapper.fromId(null)).isNull();
    }
}
