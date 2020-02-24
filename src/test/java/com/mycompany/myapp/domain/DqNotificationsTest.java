package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqNotificationsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqNotifications.class);
        DqNotifications dqNotifications1 = new DqNotifications();
        dqNotifications1.setId(1L);
        DqNotifications dqNotifications2 = new DqNotifications();
        dqNotifications2.setId(dqNotifications1.getId());
        assertThat(dqNotifications1).isEqualTo(dqNotifications2);
        dqNotifications2.setId(2L);
        assertThat(dqNotifications1).isNotEqualTo(dqNotifications2);
        dqNotifications1.setId(null);
        assertThat(dqNotifications1).isNotEqualTo(dqNotifications2);
    }
}
