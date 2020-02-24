package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleCategoriesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleCategories.class);
        DqRuleCategories dqRuleCategories1 = new DqRuleCategories();
        dqRuleCategories1.setId(1L);
        DqRuleCategories dqRuleCategories2 = new DqRuleCategories();
        dqRuleCategories2.setId(dqRuleCategories1.getId());
        assertThat(dqRuleCategories1).isEqualTo(dqRuleCategories2);
        dqRuleCategories2.setId(2L);
        assertThat(dqRuleCategories1).isNotEqualTo(dqRuleCategories2);
        dqRuleCategories1.setId(null);
        assertThat(dqRuleCategories1).isNotEqualTo(dqRuleCategories2);
    }
}
