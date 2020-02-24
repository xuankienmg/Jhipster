package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataMappingMapperTest {

    private DataMappingMapper dataMappingMapper;

    @BeforeEach
    public void setUp() {
        dataMappingMapper = new DataMappingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataMappingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataMappingMapper.fromId(null)).isNull();
    }
}
