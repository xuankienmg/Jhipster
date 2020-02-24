package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRulesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRules.class);
        DqRules dqRules1 = new DqRules();
        dqRules1.setId(1L);
        DqRules dqRules2 = new DqRules();
        dqRules2.setId(dqRules1.getId());
        assertThat(dqRules1).isEqualTo(dqRules2);
        dqRules2.setId(2L);
        assertThat(dqRules1).isNotEqualTo(dqRules2);
        dqRules1.setId(null);
        assertThat(dqRules1).isNotEqualTo(dqRules2);
    }
}
