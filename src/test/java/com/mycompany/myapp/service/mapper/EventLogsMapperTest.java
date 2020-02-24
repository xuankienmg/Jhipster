package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventLogsMapperTest {

    private EventLogsMapper eventLogsMapper;

    @BeforeEach
    public void setUp() {
        eventLogsMapper = new EventLogsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventLogsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventLogsMapper.fromId(null)).isNull();
    }
}
