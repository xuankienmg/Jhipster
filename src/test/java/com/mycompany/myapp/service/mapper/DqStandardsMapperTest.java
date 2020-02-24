package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardsMapperTest {

    private DqStandardsMapper dqStandardsMapper;

    @BeforeEach
    public void setUp() {
        dqStandardsMapper = new DqStandardsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardsMapper.fromId(null)).isNull();
    }
}
