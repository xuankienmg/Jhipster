package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EtlStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtlStatus.class);
        EtlStatus etlStatus1 = new EtlStatus();
        etlStatus1.setId(1L);
        EtlStatus etlStatus2 = new EtlStatus();
        etlStatus2.setId(etlStatus1.getId());
        assertThat(etlStatus1).isEqualTo(etlStatus2);
        etlStatus2.setId(2L);
        assertThat(etlStatus1).isNotEqualTo(etlStatus2);
        etlStatus1.setId(null);
        assertThat(etlStatus1).isNotEqualTo(etlStatus2);
    }
}
