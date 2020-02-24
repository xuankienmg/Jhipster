package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleTypesDTO.class);
        DqRuleTypesDTO dqRuleTypesDTO1 = new DqRuleTypesDTO();
        dqRuleTypesDTO1.setId(1L);
        DqRuleTypesDTO dqRuleTypesDTO2 = new DqRuleTypesDTO();
        assertThat(dqRuleTypesDTO1).isNotEqualTo(dqRuleTypesDTO2);
        dqRuleTypesDTO2.setId(dqRuleTypesDTO1.getId());
        assertThat(dqRuleTypesDTO1).isEqualTo(dqRuleTypesDTO2);
        dqRuleTypesDTO2.setId(2L);
        assertThat(dqRuleTypesDTO1).isNotEqualTo(dqRuleTypesDTO2);
        dqRuleTypesDTO1.setId(null);
        assertThat(dqRuleTypesDTO1).isNotEqualTo(dqRuleTypesDTO2);
    }
}
