package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleStatusDTO.class);
        DqRuleStatusDTO dqRuleStatusDTO1 = new DqRuleStatusDTO();
        dqRuleStatusDTO1.setId(1L);
        DqRuleStatusDTO dqRuleStatusDTO2 = new DqRuleStatusDTO();
        assertThat(dqRuleStatusDTO1).isNotEqualTo(dqRuleStatusDTO2);
        dqRuleStatusDTO2.setId(dqRuleStatusDTO1.getId());
        assertThat(dqRuleStatusDTO1).isEqualTo(dqRuleStatusDTO2);
        dqRuleStatusDTO2.setId(2L);
        assertThat(dqRuleStatusDTO1).isNotEqualTo(dqRuleStatusDTO2);
        dqRuleStatusDTO1.setId(null);
        assertThat(dqRuleStatusDTO1).isNotEqualTo(dqRuleStatusDTO2);
    }
}
