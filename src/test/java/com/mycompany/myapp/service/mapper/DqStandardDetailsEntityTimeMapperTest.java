package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityTimeMapperTest {

    private DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityTimeMapper = new DqStandardDetailsEntityTimeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityTimeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityTimeMapper.fromId(null)).isNull();
    }
}
