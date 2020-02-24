package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DqNotificationsMapperTest {

    private DqNotificationsMapper dqNotificationsMapper;

    @BeforeEach
    public void setUp() {
        dqNotificationsMapper = new DqNotificationsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dqNotificationsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dqNotificationsMapper.fromId(null)).isNull();
    }
}
