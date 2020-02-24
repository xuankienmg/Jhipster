package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityTextTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityText.class);
        DqStandardDetailsEntityText dqStandardDetailsEntityText1 = new DqStandardDetailsEntityText();
        dqStandardDetailsEntityText1.setId(1L);
        DqStandardDetailsEntityText dqStandardDetailsEntityText2 = new DqStandardDetailsEntityText();
        dqStandardDetailsEntityText2.setId(dqStandardDetailsEntityText1.getId());
        assertThat(dqStandardDetailsEntityText1).isEqualTo(dqStandardDetailsEntityText2);
        dqStandardDetailsEntityText2.setId(2L);
        assertThat(dqStandardDetailsEntityText1).isNotEqualTo(dqStandardDetailsEntityText2);
        dqStandardDetailsEntityText1.setId(null);
        assertThat(dqStandardDetailsEntityText1).isNotEqualTo(dqStandardDetailsEntityText2);
    }
}
