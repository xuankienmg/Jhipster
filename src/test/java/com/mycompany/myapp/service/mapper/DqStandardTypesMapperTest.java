package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardTypesMapperTest {

    private DqStandardTypesMapper dqStandardTypesMapper;

    @BeforeEach
    public void setUp() {
        dqStandardTypesMapper = new DqStandardTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardTypesMapper.fromId(null)).isNull();
    }
}
