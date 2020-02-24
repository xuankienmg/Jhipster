package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityTimeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityTime.class);
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime1 = new DqStandardDetailsEntityTime();
        dqStandardDetailsEntityTime1.setId(1L);
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime2 = new DqStandardDetailsEntityTime();
        dqStandardDetailsEntityTime2.setId(dqStandardDetailsEntityTime1.getId());
        assertThat(dqStandardDetailsEntityTime1).isEqualTo(dqStandardDetailsEntityTime2);
        dqStandardDetailsEntityTime2.setId(2L);
        assertThat(dqStandardDetailsEntityTime1).isNotEqualTo(dqStandardDetailsEntityTime2);
        dqStandardDetailsEntityTime1.setId(null);
        assertThat(dqStandardDetailsEntityTime1).isNotEqualTo(dqStandardDetailsEntityTime2);
    }
}
