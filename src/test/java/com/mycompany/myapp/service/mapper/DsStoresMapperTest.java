package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsStoresMapperTest {

    private DsStoresMapper dsStoresMapper;

    @BeforeEach
    public void setUp() {
        dsStoresMapper = new DsStoresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsStoresMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsStoresMapper.fromId(null)).isNull();
    }
}
