package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityIntMapperTest {

    private DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityIntMapper = new DqStandardDetailsEntityIntMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityIntMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityIntMapper.fromId(null)).isNull();
    }
}
