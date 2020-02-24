package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DsColumnsMapperTest {

    private DsColumnsMapper dsColumnsMapper;

    @BeforeEach
    public void setUp() {
        dsColumnsMapper = new DsColumnsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dsColumnsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dsColumnsMapper.fromId(null)).isNull();
    }
}
