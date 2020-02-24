package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsColumnTypesMapperTest {

    private DsColumnTypesMapper dsColumnTypesMapper;

    @BeforeEach
    public void setUp() {
        dsColumnTypesMapper = new DsColumnTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsColumnTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsColumnTypesMapper.fromId(null)).isNull();
    }
}
