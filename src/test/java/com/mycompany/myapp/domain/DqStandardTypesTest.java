package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardTypes.class);
        DqStandardTypes dqStandardTypes1 = new DqStandardTypes();
        dqStandardTypes1.setId(1L);
        DqStandardTypes dqStandardTypes2 = new DqStandardTypes();
        dqStandardTypes2.setId(dqStandardTypes1.getId());
        assertThat(dqStandardTypes1).isEqualTo(dqStandardTypes2);
        dqStandardTypes2.setId(2L);
        assertThat(dqStandardTypes1).isNotEqualTo(dqStandardTypes2);
        dqStandardTypes1.setId(null);
        assertThat(dqStandardTypes1).isNotEqualTo(dqStandardTypes2);
    }
}
