package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleRiskLevelsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleRiskLevelsDTO.class);
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO1 = new DqRuleRiskLevelsDTO();
        dqRuleRiskLevelsDTO1.setId(1L);
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO2 = new DqRuleRiskLevelsDTO();
        assertThat(dqRuleRiskLevelsDTO1).isNotEqualTo(dqRuleRiskLevelsDTO2);
        dqRuleRiskLevelsDTO2.setId(dqRuleRiskLevelsDTO1.getId());
        assertThat(dqRuleRiskLevelsDTO1).isEqualTo(dqRuleRiskLevelsDTO2);
        dqRuleRiskLevelsDTO2.setId(2L);
        assertThat(dqRuleRiskLevelsDTO1).isNotEqualTo(dqRuleRiskLevelsDTO2);
        dqRuleRiskLevelsDTO1.setId(null);
        assertThat(dqRuleRiskLevelsDTO1).isNotEqualTo(dqRuleRiskLevelsDTO2);
    }
}
