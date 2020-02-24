package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EtlPackagesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtlPackages.class);
        EtlPackages etlPackages1 = new EtlPackages();
        etlPackages1.setId(1L);
        EtlPackages etlPackages2 = new EtlPackages();
        etlPackages2.setId(etlPackages1.getId());
        assertThat(etlPackages1).isEqualTo(etlPackages2);
        etlPackages2.setId(2L);
        assertThat(etlPackages1).isNotEqualTo(etlPackages2);
        etlPackages1.setId(null);
        assertThat(etlPackages1).isNotEqualTo(etlPackages2);
    }
}
