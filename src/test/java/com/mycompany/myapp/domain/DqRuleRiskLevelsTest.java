package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleRiskLevelsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleRiskLevels.class);
        DqRuleRiskLevels dqRuleRiskLevels1 = new DqRuleRiskLevels();
        dqRuleRiskLevels1.setId(1L);
        DqRuleRiskLevels dqRuleRiskLevels2 = new DqRuleRiskLevels();
        dqRuleRiskLevels2.setId(dqRuleRiskLevels1.getId());
        assertThat(dqRuleRiskLevels1).isEqualTo(dqRuleRiskLevels2);
        dqRuleRiskLevels2.setId(2L);
        assertThat(dqRuleRiskLevels1).isNotEqualTo(dqRuleRiskLevels2);
        dqRuleRiskLevels1.setId(null);
        assertThat(dqRuleRiskLevels1).isNotEqualTo(dqRuleRiskLevels2);
    }
}
