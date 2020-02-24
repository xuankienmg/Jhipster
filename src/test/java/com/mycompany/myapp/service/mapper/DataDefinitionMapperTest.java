package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataDefinitionMapperTest {

    private DataDefinitionMapper dataDefinitionMapper;

    @BeforeEach
    public void setUp() {
        dataDefinitionMapper = new DataDefinitionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataDefinitionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataDefinitionMapper.fromId(null)).isNull();
    }
}
