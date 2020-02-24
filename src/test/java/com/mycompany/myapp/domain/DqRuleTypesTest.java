package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleTypes.class);
        DqRuleTypes dqRuleTypes1 = new DqRuleTypes();
        dqRuleTypes1.setId(1L);
        DqRuleTypes dqRuleTypes2 = new DqRuleTypes();
        dqRuleTypes2.setId(dqRuleTypes1.getId());
        assertThat(dqRuleTypes1).isEqualTo(dqRuleTypes2);
        dqRuleTypes2.setId(2L);
        assertThat(dqRuleTypes1).isNotEqualTo(dqRuleTypes2);
        dqRuleTypes1.setId(null);
        assertThat(dqRuleTypes1).isNotEqualTo(dqRuleTypes2);
    }
}
