package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataFlowsMapperTest {

    private DataFlowsMapper dataFlowsMapper;

    @BeforeEach
    public void setUp() {
        dataFlowsMapper = new DataFlowsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataFlowsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataFlowsMapper.fromId(null)).isNull();
    }
}
