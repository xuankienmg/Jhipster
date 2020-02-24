package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityVarcharMapperTest {

    private DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityVarcharMapper = new DqStandardDetailsEntityVarcharMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityVarcharMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityVarcharMapper.fromId(null)).isNull();
    }
}
