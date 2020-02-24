package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityIntTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityInt.class);
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt1 = new DqStandardDetailsEntityInt();
        dqStandardDetailsEntityInt1.setId(1L);
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt2 = new DqStandardDetailsEntityInt();
        dqStandardDetailsEntityInt2.setId(dqStandardDetailsEntityInt1.getId());
        assertThat(dqStandardDetailsEntityInt1).isEqualTo(dqStandardDetailsEntityInt2);
        dqStandardDetailsEntityInt2.setId(2L);
        assertThat(dqStandardDetailsEntityInt1).isNotEqualTo(dqStandardDetailsEntityInt2);
        dqStandardDetailsEntityInt1.setId(null);
        assertThat(dqStandardDetailsEntityInt1).isNotEqualTo(dqStandardDetailsEntityInt2);
    }
}
