package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityVarcharTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityVarchar.class);
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar1 = new DqStandardDetailsEntityVarchar();
        dqStandardDetailsEntityVarchar1.setId(1L);
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar2 = new DqStandardDetailsEntityVarchar();
        dqStandardDetailsEntityVarchar2.setId(dqStandardDetailsEntityVarchar1.getId());
        assertThat(dqStandardDetailsEntityVarchar1).isEqualTo(dqStandardDetailsEntityVarchar2);
        dqStandardDetailsEntityVarchar2.setId(2L);
        assertThat(dqStandardDetailsEntityVarchar1).isNotEqualTo(dqStandardDetailsEntityVarchar2);
        dqStandardDetailsEntityVarchar1.setId(null);
        assertThat(dqStandardDetailsEntityVarchar1).isNotEqualTo(dqStandardDetailsEntityVarchar2);
    }
}
