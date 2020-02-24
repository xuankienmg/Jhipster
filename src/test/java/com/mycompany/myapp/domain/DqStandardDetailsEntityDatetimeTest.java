package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityDatetimeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityDatetime.class);
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime1 = new DqStandardDetailsEntityDatetime();
        dqStandardDetailsEntityDatetime1.setId(1L);
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime2 = new DqStandardDetailsEntityDatetime();
        dqStandardDetailsEntityDatetime2.setId(dqStandardDetailsEntityDatetime1.getId());
        assertThat(dqStandardDetailsEntityDatetime1).isEqualTo(dqStandardDetailsEntityDatetime2);
        dqStandardDetailsEntityDatetime2.setId(2L);
        assertThat(dqStandardDetailsEntityDatetime1).isNotEqualTo(dqStandardDetailsEntityDatetime2);
        dqStandardDetailsEntityDatetime1.setId(null);
        assertThat(dqStandardDetailsEntityDatetime1).isNotEqualTo(dqStandardDetailsEntityDatetime2);
    }
}
