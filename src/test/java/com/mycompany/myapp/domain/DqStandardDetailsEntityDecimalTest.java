package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityDecimalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityDecimal.class);
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal1 = new DqStandardDetailsEntityDecimal();
        dqStandardDetailsEntityDecimal1.setId(1L);
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal2 = new DqStandardDetailsEntityDecimal();
        dqStandardDetailsEntityDecimal2.setId(dqStandardDetailsEntityDecimal1.getId());
        assertThat(dqStandardDetailsEntityDecimal1).isEqualTo(dqStandardDetailsEntityDecimal2);
        dqStandardDetailsEntityDecimal2.setId(2L);
        assertThat(dqStandardDetailsEntityDecimal1).isNotEqualTo(dqStandardDetailsEntityDecimal2);
        dqStandardDetailsEntityDecimal1.setId(null);
        assertThat(dqStandardDetailsEntityDecimal1).isNotEqualTo(dqStandardDetailsEntityDecimal2);
    }
}
