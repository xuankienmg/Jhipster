package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataDefinition.
 */
@Entity
@Table(name = "data_definition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "src_col_id")
    private Integer srcColId;

    @Column(name = "def_description")
    private String defDescription;

    @Column(name = "def_sample_data")
    private String defSampleData;

    @ManyToOne
    @JsonIgnoreProperties("dataDefinitions")
    private DsColumns col;

    @ManyToOne
    @JsonIgnoreProperties("dataDefinitions")
    private DsColumnTypes type;

    @ManyToOne
    @JsonIgnoreProperties("dataDefinitions")
    private DsTables tbl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSrcColId() {
        return srcColId;
    }

    public DataDefinition srcColId(Integer srcColId) {
        this.srcColId = srcColId;
        return this;
    }

    public void setSrcColId(Integer srcColId) {
        this.srcColId = srcColId;
    }

    public String getDefDescription() {
        return defDescription;
    }

    public DataDefinition defDescription(String defDescription) {
        this.defDescription = defDescription;
        return this;
    }

    public void setDefDescription(String defDescription) {
        this.defDescription = defDescription;
    }

    public String getDefSampleData() {
        return defSampleData;
    }

    public DataDefinition defSampleData(String defSampleData) {
        this.defSampleData = defSampleData;
        return this;
    }

    public void setDefSampleData(String defSampleData) {
        this.defSampleData = defSampleData;
    }

    public DsColumns getCol() {
        return col;
    }

    public DataDefinition col(DsColumns dsColumns) {
        this.col = dsColumns;
        return this;
    }

    public void setCol(DsColumns dsColumns) {
        this.col = dsColumns;
    }

    public DsColumnTypes getType() {
        return type;
    }

    public DataDefinition type(DsColumnTypes dsColumnTypes) {
        this.type = dsColumnTypes;
        return this;
    }

    public void setType(DsColumnTypes dsColumnTypes) {
        this.type = dsColumnTypes;
    }

    public DsTables getTbl() {
        return tbl;
    }

    public DataDefinition tbl(DsTables dsTables) {
        this.tbl = dsTables;
        return this;
    }

    public void setTbl(DsTables dsTables) {
        this.tbl = dsTables;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataDefinition)) {
            return false;
        }
        return id != null && id.equals(((DataDefinition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataDefinition{" +
            "id=" + getId() +
            ", srcColId=" + getSrcColId() +
            ", defDescription='" + getDefDescription() + "'" +
            ", defSampleData='" + getDefSampleData() + "'" +
            "}";
    }
}
