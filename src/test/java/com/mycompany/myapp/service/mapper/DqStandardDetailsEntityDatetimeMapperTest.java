package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityDatetimeMapperTest {

    private DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityDatetimeMapper = new DqStandardDetailsEntityDatetimeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityDatetimeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityDatetimeMapper.fromId(null)).isNull();
    }
}
