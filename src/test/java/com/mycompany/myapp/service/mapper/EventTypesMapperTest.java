package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventTypesMapperTest {

    private EventTypesMapper eventTypesMapper;

    @BeforeEach
    public void setUp() {
        eventTypesMapper = new EventTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventTypesMapper.fromId(null)).isNull();
    }
}
