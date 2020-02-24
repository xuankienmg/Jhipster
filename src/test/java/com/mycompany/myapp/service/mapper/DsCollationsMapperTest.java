package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsCollationsMapperTest {

    private DsCollationsMapper dsCollationsMapper;

    @BeforeEach
    public void setUp() {
        dsCollationsMapper = new DsCollationsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsCollationsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsCollationsMapper.fromId(null)).isNull();
    }
}
