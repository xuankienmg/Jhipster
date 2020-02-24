package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataFlowsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataFlows.class);
        DataFlows dataFlows1 = new DataFlows();
        dataFlows1.setId(1L);
        DataFlows dataFlows2 = new DataFlows();
        dataFlows2.setId(dataFlows1.getId());
        assertThat(dataFlows1).isEqualTo(dataFlows2);
        dataFlows2.setId(2L);
        assertThat(dataFlows1).isNotEqualTo(dataFlows2);
        dataFlows1.setId(null);
        assertThat(dataFlows1).isNotEqualTo(dataFlows2);
    }
}
