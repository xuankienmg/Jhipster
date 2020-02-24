package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsTablesMapperTest {

    private DsTablesMapper dsTablesMapper;

    @BeforeEach
    public void setUp() {
        dsTablesMapper = new DsTablesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsTablesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsTablesMapper.fromId(null)).isNull();
    }
}
