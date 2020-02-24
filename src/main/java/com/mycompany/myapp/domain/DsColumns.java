package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DsColumns.
 */
@Entity
@Table(name = "ds_columns")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsColumns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "col_name")
    private String colName;

    @Column(name = "col_data_type")
    private String colDataType;

    @Column(name = "is_primary_key")
    private Boolean isPrimaryKey;

    @Column(name = "is_foreign_key")
    private Boolean isForeignKey;

    @Column(name = "is_identity")
    private Boolean isIdentity;

    @Column(name = "is_null")
    private Boolean isNull;

    @ManyToOne
    @JsonIgnoreProperties("dsColumns")
    private DsTables colTbl;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ds_columns_rule",
               joinColumns = @JoinColumn(name = "ds_columns_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "rule_id", referencedColumnName = "id"))
    private Set<DqRules> rules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public DsColumns colName(String colName) {
        this.colName = colName;
        return this;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColDataType() {
        return colDataType;
    }

    public DsColumns colDataType(String colDataType) {
        this.colDataType = colDataType;
        return this;
    }

    public void setColDataType(String colDataType) {
        this.colDataType = colDataType;
    }

    public Boolean isIsPrimaryKey() {
        return isPrimaryKey;
    }

    public DsColumns isPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
        return this;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Boolean isIsForeignKey() {
        return isForeignKey;
    }

    public DsColumns isForeignKey(Boolean isForeignKey) {
        this.isForeignKey = isForeignKey;
        return this;
    }

    public void setIsForeignKey(Boolean isForeignKey) {
        this.isForeignKey = isForeignKey;
    }

    public Boolean isIsIdentity() {
        return isIdentity;
    }

    public DsColumns isIdentity(Boolean isIdentity) {
        this.isIdentity = isIdentity;
        return this;
    }

    public void setIsIdentity(Boolean isIdentity) {
        this.isIdentity = isIdentity;
    }

    public Boolean isIsNull() {
        return isNull;
    }

    public DsColumns isNull(Boolean isNull) {
        this.isNull = isNull;
        return this;
    }

    public void setIsNull(Boolean isNull) {
        this.isNull = isNull;
    }

    public DsTables getColTbl() {
        return colTbl;
    }

    public DsColumns colTbl(DsTables dsTables) {
        this.colTbl = dsTables;
        return this;
    }

    public void setColTbl(DsTables dsTables) {
        this.colTbl = dsTables;
    }

    public Set<DqRules> getRules() {
        return rules;
    }

    public DsColumns rules(Set<DqRules> dqRules) {
        this.rules = dqRules;
        return this;
    }

    public DsColumns addRule(DqRules dqRules) {
        this.rules.add(dqRules);
        dqRules.getCols().add(this);
        return this;
    }

    public DsColumns removeRule(DqRules dqRules) {
        this.rules.remove(dqRules);
        dqRules.getCols().remove(this);
        return this;
    }

    public void setRules(Set<DqRules> dqRules) {
        this.rules = dqRules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsColumns)) {
            return false;
        }
        return id != null && id.equals(((DsColumns) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsColumns{" +
            "id=" + getId() +
            ", colName='" + getColName() + "'" +
            ", colDataType='" + getColDataType() + "'" +
            ", isPrimaryKey='" + isIsPrimaryKey() + "'" +
            ", isForeignKey='" + isIsForeignKey() + "'" +
            ", isIdentity='" + isIsIdentity() + "'" +
            ", isNull='" + isIsNull() + "'" +
            "}";
    }
}
