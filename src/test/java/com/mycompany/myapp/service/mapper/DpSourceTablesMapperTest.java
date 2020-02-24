package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DpSourceTablesMapperTest {

    private DpSourceTablesMapper dpSourceTablesMapper;

    @BeforeEach
    public void setUp() {
        dpSourceTablesMapper = new DpSourceTablesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dpSourceTablesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dpSourceTablesMapper.fromId(null)).isNull();
    }
}
