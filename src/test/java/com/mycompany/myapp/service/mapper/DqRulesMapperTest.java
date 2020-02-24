package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRulesMapperTest {

    private DqRulesMapper dqRulesMapper;

    @BeforeEach
    public void setUp() {
        dqRulesMapper = new DqRulesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRulesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRulesMapper.fromId(null)).isNull();
    }
}
