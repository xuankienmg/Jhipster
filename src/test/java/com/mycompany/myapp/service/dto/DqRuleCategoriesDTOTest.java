package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqRuleCategoriesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqRuleCategoriesDTO.class);
        DqRuleCategoriesDTO dqRuleCategoriesDTO1 = new DqRuleCategoriesDTO();
        dqRuleCategoriesDTO1.setId(1L);
        DqRuleCategoriesDTO dqRuleCategoriesDTO2 = new DqRuleCategoriesDTO();
        assertThat(dqRuleCategoriesDTO1).isNotEqualTo(dqRuleCategoriesDTO2);
        dqRuleCategoriesDTO2.setId(dqRuleCategoriesDTO1.getId());
        assertThat(dqRuleCategoriesDTO1).isEqualTo(dqRuleCategoriesDTO2);
        dqRuleCategoriesDTO2.setId(2L);
        assertThat(dqRuleCategoriesDTO1).isNotEqualTo(dqRuleCategoriesDTO2);
        dqRuleCategoriesDTO1.setId(null);
        assertThat(dqRuleCategoriesDTO1).isNotEqualTo(dqRuleCategoriesDTO2);
    }
}
