package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleActionsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleActionsDTO.class);
        DqRuleActionsDTO dqRuleActionsDTO1 = new DqRuleActionsDTO();
        dqRuleActionsDTO1.setId(1L);
        DqRuleActionsDTO dqRuleActionsDTO2 = new DqRuleActionsDTO();
        assertThat(dqRuleActionsDTO1).isNotEqualTo(dqRuleActionsDTO2);
        dqRuleActionsDTO2.setId(dqRuleActionsDTO1.getId());
        assertThat(dqRuleActionsDTO1).isEqualTo(dqRuleActionsDTO2);
        dqRuleActionsDTO2.setId(2L);
        assertThat(dqRuleActionsDTO1).isNotEqualTo(dqRuleActionsDTO2);
        dqRuleActionsDTO1.setId(null);
        assertThat(dqRuleActionsDTO1).isNotEqualTo(dqRuleActionsDTO2);
    }
}
