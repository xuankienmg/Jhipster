package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataFlowsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataFlowsDTO.class);
        DataFlowsDTO dataFlowsDTO1 = new DataFlowsDTO();
        dataFlowsDTO1.setId(1L);
        DataFlowsDTO dataFlowsDTO2 = new DataFlowsDTO();
        assertThat(dataFlowsDTO1).isNotEqualTo(dataFlowsDTO2);
        dataFlowsDTO2.setId(dataFlowsDTO1.getId());
        assertThat(dataFlowsDTO1).isEqualTo(dataFlowsDTO2);
        dataFlowsDTO2.setId(2L);
        assertThat(dataFlowsDTO1).isNotEqualTo(dataFlowsDTO2);
        dataFlowsDTO1.setId(null);
        assertThat(dataFlowsDTO1).isNotEqualTo(dataFlowsDTO2);
    }
}
