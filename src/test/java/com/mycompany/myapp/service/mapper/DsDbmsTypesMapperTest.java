package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsDbmsTypesMapperTest {

    private DsDbmsTypesMapper dsDbmsTypesMapper;

    @BeforeEach
    public void setUp() {
        dsDbmsTypesMapper = new DsDbmsTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsDbmsTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsDbmsTypesMapper.fromId(null)).isNull();
    }
}
