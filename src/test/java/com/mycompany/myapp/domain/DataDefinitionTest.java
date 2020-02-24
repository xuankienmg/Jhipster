package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataDefinitionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataDefinition.class);
        DataDefinition dataDefinition1 = new DataDefinition();
        dataDefinition1.setId(1L);
        DataDefinition dataDefinition2 = new DataDefinition();
        dataDefinition2.setId(dataDefinition1.getId());
        assertThat(dataDefinition1).isEqualTo(dataDefinition2);
        dataDefinition2.setId(2L);
        assertThat(dataDefinition1).isNotEqualTo(dataDefinition2);
        dataDefinition1.setId(null);
        assertThat(dataDefinition1).isNotEqualTo(dataDefinition2);
    }
}
