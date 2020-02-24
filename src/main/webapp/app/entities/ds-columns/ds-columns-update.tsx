import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsTables } from 'app/shared/model/ds-tables.model';
import { getEntities as getDsTables } from 'app/entities/ds-tables/ds-tables.reducer';
import { IDqRules } from 'app/shared/model/dq-rules.model';
import { getEntities as getDqRules } from 'app/entities/dq-rules/dq-rules.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ds-columns.reducer';
import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsColumnsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsColumnsUpdate = (props: IDsColumnsUpdateProps) => {
  const [idsrule, setIdsrule] = useState([]);
  const [colTblId, setColTblId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsColumnsEntity, dsTables, dqRules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-columns' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsTables();
    props.getDqRules();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dsColumnsEntity,
        ...values,
        rules: mapIdList(values.rules)
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterApp.dsColumns.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsColumns.home.createOrEditLabel">Create or edit a DsColumns</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsColumnsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-columns-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-columns-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="colNameLabel" for="ds-columns-colName">
                  <Translate contentKey="jhipsterApp.dsColumns.colName">Col Name</Translate>
                </Label>
                <AvField id="ds-columns-colName" type="text" name="colName" />
              </AvGroup>
              <AvGroup>
                <Label id="colDataTypeLabel" for="ds-columns-colDataType">
                  <Translate contentKey="jhipsterApp.dsColumns.colDataType">Col Data Type</Translate>
                </Label>
                <AvField id="ds-columns-colDataType" type="text" name="colDataType" />
              </AvGroup>
              <AvGroup check>
                <Label id="isPrimaryKeyLabel">
                  <AvInput id="ds-columns-isPrimaryKey" type="checkbox" className="form-check-input" name="isPrimaryKey" />
                  <Translate contentKey="jhipsterApp.dsColumns.isPrimaryKey">Is Primary Key</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isForeignKeyLabel">
                  <AvInput id="ds-columns-isForeignKey" type="checkbox" className="form-check-input" name="isForeignKey" />
                  <Translate contentKey="jhipsterApp.dsColumns.isForeignKey">Is Foreign Key</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isIdentityLabel">
                  <AvInput id="ds-columns-isIdentity" type="checkbox" className="form-check-input" name="isIdentity" />
                  <Translate contentKey="jhipsterApp.dsColumns.isIdentity">Is Identity</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isNullLabel">
                  <AvInput id="ds-columns-isNull" type="checkbox" className="form-check-input" name="isNull" />
                  <Translate contentKey="jhipsterApp.dsColumns.isNull">Is Null</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="ds-columns-colTbl">
                  <Translate contentKey="jhipsterApp.dsColumns.colTbl">Col Tbl</Translate>
                </Label>
                <AvInput id="ds-columns-colTbl" type="select" className="form-control" name="colTblId">
                  <option value="" key="0" />
                  {dsTables
                    ? dsTables.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="ds-columns-rule">
                  <Translate contentKey="jhipsterApp.dsColumns.rule">Rule</Translate>
                </Label>
                <AvInput
                  id="ds-columns-rule"
                  type="select"
                  multiple
                  className="form-control"
                  name="rules"
                  value={dsColumnsEntity.rules && dsColumnsEntity.rules.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {dqRules
                    ? dqRules.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-columns" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  dsTables: storeState.dsTables.entities,
  dqRules: storeState.dqRules.entities,
  dsColumnsEntity: storeState.dsColumns.entity,
  loading: storeState.dsColumns.loading,
  updating: storeState.dsColumns.updating,
  updateSuccess: storeState.dsColumns.updateSuccess
});

const mapDispatchToProps = {
  getDsTables,
  getDqRules,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsColumnsUpdate);
