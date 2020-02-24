package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityDecimalMapperTest {

    private DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityDecimalMapper = new DqStandardDetailsEntityDecimalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityDecimalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityDecimalMapper.fromId(null)).isNull();
    }
}
