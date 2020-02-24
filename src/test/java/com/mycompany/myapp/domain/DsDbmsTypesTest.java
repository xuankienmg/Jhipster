package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsDbmsTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsDbmsTypes.class);
        DsDbmsTypes dsDbmsTypes1 = new DsDbmsTypes();
        dsDbmsTypes1.setId(1L);
        DsDbmsTypes dsDbmsTypes2 = new DsDbmsTypes();
        dsDbmsTypes2.setId(dsDbmsTypes1.getId());
        assertThat(dsDbmsTypes1).isEqualTo(dsDbmsTypes2);
        dsDbmsTypes2.setId(2L);
        assertThat(dsDbmsTypes1).isNotEqualTo(dsDbmsTypes2);
        dsDbmsTypes1.setId(null);
        assertThat(dsDbmsTypes1).isNotEqualTo(dsDbmsTypes2);
    }
}
