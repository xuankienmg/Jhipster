package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsTableTypesMapperTest {

    private DsTableTypesMapper dsTableTypesMapper;

    @BeforeEach
    public void setUp() {
        dsTableTypesMapper = new DsTableTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsTableTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsTableTypesMapper.fromId(null)).isNull();
    }
}
