package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleActionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleActions.class);
        DqRuleActions dqRuleActions1 = new DqRuleActions();
        dqRuleActions1.setId(1L);
        DqRuleActions dqRuleActions2 = new DqRuleActions();
        dqRuleActions2.setId(dqRuleActions1.getId());
        assertThat(dqRuleActions1).isEqualTo(dqRuleActions2);
        dqRuleActions2.setId(2L);
        assertThat(dqRuleActions1).isNotEqualTo(dqRuleActions2);
        dqRuleActions1.setId(null);
        assertThat(dqRuleActions1).isNotEqualTo(dqRuleActions2);
    }
}
