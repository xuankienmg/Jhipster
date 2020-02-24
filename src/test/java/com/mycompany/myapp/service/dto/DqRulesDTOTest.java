package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRulesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRulesDTO.class);
        DqRulesDTO dqRulesDTO1 = new DqRulesDTO();
        dqRulesDTO1.setId(1L);
        DqRulesDTO dqRulesDTO2 = new DqRulesDTO();
        assertThat(dqRulesDTO1).isNotEqualTo(dqRulesDTO2);
        dqRulesDTO2.setId(dqRulesDTO1.getId());
        assertThat(dqRulesDTO1).isEqualTo(dqRulesDTO2);
        dqRulesDTO2.setId(2L);
        assertThat(dqRulesDTO1).isNotEqualTo(dqRulesDTO2);
        dqRulesDTO1.setId(null);
        assertThat(dqRulesDTO1).isNotEqualTo(dqRulesDTO2);
    }
}
