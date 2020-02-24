package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRuleRiskLevelsMapperTest {

    private DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper;

    @BeforeEach
    public void setUp() {
        dqRuleRiskLevelsMapper = new DqRuleRiskLevelsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRuleRiskLevelsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRuleRiskLevelsMapper.fromId(null)).isNull();
    }
}
