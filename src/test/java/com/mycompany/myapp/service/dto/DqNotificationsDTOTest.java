package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqNotificationsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqNotificationsDTO.class);
        DqNotificationsDTO dqNotificationsDTO1 = new DqNotificationsDTO();
        dqNotificationsDTO1.setId(1L);
        DqNotificationsDTO dqNotificationsDTO2 = new DqNotificationsDTO();
        assertThat(dqNotificationsDTO1).isNotEqualTo(dqNotificationsDTO2);
        dqNotificationsDTO2.setId(dqNotificationsDTO1.getId());
        assertThat(dqNotificationsDTO1).isEqualTo(dqNotificationsDTO2);
        dqNotificationsDTO2.setId(2L);
        assertThat(dqNotificationsDTO1).isNotEqualTo(dqNotificationsDTO2);
        dqNotificationsDTO1.setId(null);
        assertThat(dqNotificationsDTO1).isNotEqualTo(dqNotificationsDTO2);
    }
}
