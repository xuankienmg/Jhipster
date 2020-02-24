package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleStatus.class);
        DqRuleStatus dqRuleStatus1 = new DqRuleStatus();
        dqRuleStatus1.setId(1L);
        DqRuleStatus dqRuleStatus2 = new DqRuleStatus();
        dqRuleStatus2.setId(dqRuleStatus1.getId());
        assertThat(dqRuleStatus1).isEqualTo(dqRuleStatus2);
        dqRuleStatus2.setId(2L);
        assertThat(dqRuleStatus1).isNotEqualTo(dqRuleStatus2);
        dqRuleStatus1.setId(null);
        assertThat(dqRuleStatus1).isNotEqualTo(dqRuleStatus2);
    }
}
