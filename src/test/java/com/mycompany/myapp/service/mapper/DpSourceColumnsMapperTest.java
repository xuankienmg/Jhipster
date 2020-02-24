package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DpSourceColumnsMapperTest {

    private DpSourceColumnsMapper dpSourceColumnsMapper;

    @BeforeEach
    public void setUp() {
        dpSourceColumnsMapper = new DpSourceColumnsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dpSourceColumnsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dpSourceColumnsMapper.fromId(null)).isNull();
    }
}
