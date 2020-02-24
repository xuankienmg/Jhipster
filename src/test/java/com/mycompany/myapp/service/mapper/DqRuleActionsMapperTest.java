package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRuleActionsMapperTest {

    private DqRuleActionsMapper dqRuleActionsMapper;

    @BeforeEach
    public void setUp() {
        dqRuleActionsMapper = new DqRuleActionsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRuleActionsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRuleActionsMapper.fromId(null)).isNull();
    }
}
