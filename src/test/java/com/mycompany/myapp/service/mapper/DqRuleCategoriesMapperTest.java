package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqRuleCategoriesMapperTest {

    private DqRuleCategoriesMapper dqRuleCategoriesMapper;

    @BeforeEach
    public void setUp() {
        dqRuleCategoriesMapper = new DqRuleCategoriesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqRuleCategoriesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqRuleCategoriesMapper.fromId(null)).isNull();
    }
}
