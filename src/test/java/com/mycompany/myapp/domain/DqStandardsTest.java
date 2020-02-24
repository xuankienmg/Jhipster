package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandards.class);
        DqStandards dqStandards1 = new DqStandards();
        dqStandards1.setId(1L);
        DqStandards dqStandards2 = new DqStandards();
        dqStandards2.setId(dqStandards1.getId());
        assertThat(dqStandards1).isEqualTo(dqStandards2);
        dqStandards2.setId(2L);
        assertThat(dqStandards1).isNotEqualTo(dqStandards2);
        dqStandards1.setId(null);
        assertThat(dqStandards1).isNotEqualTo(dqStandards2);
    }
}
