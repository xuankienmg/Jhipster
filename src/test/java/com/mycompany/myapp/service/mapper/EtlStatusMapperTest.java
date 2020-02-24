package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtlStatusMapperTest {

    private EtlStatusMapper etlStatusMapper;

    @BeforeEach
    public void setUp() {
        etlStatusMapper = new EtlStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etlStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etlStatusMapper.fromId(null)).isNull();
    }
}
