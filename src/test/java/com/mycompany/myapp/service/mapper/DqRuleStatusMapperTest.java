package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRuleStatusMapperTest {

    private DqRuleStatusMapper dqRuleStatusMapper;

    @BeforeEach
    public void setUp() {
        dqRuleStatusMapper = new DqRuleStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRuleStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRuleStatusMapper.fromId(null)).isNull();
    }
}
