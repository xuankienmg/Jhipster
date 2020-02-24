package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqStandardDetailsEntityTextMapperTest {

    private DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper;

    @BeforeEach
    public void setUp() {
        dqStandardDetailsEntityTextMapper = new DqStandardDetailsEntityTextMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqStandardDetailsEntityTextMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqStandardDetailsEntityTextMapper.fromId(null)).isNull();
    }
}
