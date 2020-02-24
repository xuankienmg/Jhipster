package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataMappingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataMapping.class);
        DataMapping dataMapping1 = new DataMapping();
        dataMapping1.setId(1L);
        DataMapping dataMapping2 = new DataMapping();
        dataMapping2.setId(dataMapping1.getId());
        assertThat(dataMapping1).isEqualTo(dataMapping2);
        dataMapping2.setId(2L);
        assertThat(dataMapping1).isNotEqualTo(dataMapping2);
        dataMapping1.setId(null);
        assertThat(dataMapping1).isNotEqualTo(dataMapping2);
    }
}
