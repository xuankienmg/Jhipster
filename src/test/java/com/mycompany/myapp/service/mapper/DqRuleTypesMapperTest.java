package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRuleTypesMapperTest {

    private DqRuleTypesMapper dqRuleTypesMapper;

    @BeforeEach
    public void setUp() {
        dqRuleTypesMapper = new DqRuleTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRuleTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRuleTypesMapper.fromId(null)).isNull();
    }
}
