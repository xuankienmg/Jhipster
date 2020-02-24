import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { getEntities as getDsColumns } from 'app/entities/ds-columns/ds-columns.reducer';
import { IDsColumnTypes } from 'app/shared/model/ds-column-types.model';
import { getEntities as getDsColumnTypes } from 'app/entities/ds-column-types/ds-column-types.reducer';
import { IDsTables } from 'app/shared/model/ds-tables.model';
import { getEntities as getDsTables } from 'app/entities/ds-tables/ds-tables.reducer';
import { getEntity, updateEntity, createEntity, reset } from './data-definition.reducer';
import { IDataDefinition } from 'app/shared/model/data-definition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDataDefinitionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataDefinitionUpdate = (props: IDataDefinitionUpdateProps) => {
  const [colId, setColId] = useState('0');
  const [typeId, setTypeId] = useState('0');
  const [tblId, setTblId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dataDefinitionEntity, dsColumns, dsColumnTypes, dsTables, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/data-definition' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsColumns();
    props.getDsColumnTypes();
    props.getDsTables();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dataDefinitionEntity,
        ...values
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
          <h2 id="jhipsterApp.dataDefinition.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dataDefinition.home.createOrEditLabel">Create or edit a DataDefinition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dataDefinitionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="data-definition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="data-definition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="srcColIdLabel" for="data-definition-srcColId">
                  <Translate contentKey="jhipsterApp.dataDefinition.srcColId">Src Col Id</Translate>
                </Label>
                <AvField id="data-definition-srcColId" type="string" className="form-control" name="srcColId" />
              </AvGroup>
              <AvGroup>
                <Label id="defDescriptionLabel" for="data-definition-defDescription">
                  <Translate contentKey="jhipsterApp.dataDefinition.defDescription">Def Description</Translate>
                </Label>
                <AvField id="data-definition-defDescription" type="text" name="defDescription" />
              </AvGroup>
              <AvGroup>
                <Label id="defSampleDataLabel" for="data-definition-defSampleData">
                  <Translate contentKey="jhipsterApp.dataDefinition.defSampleData">Def Sample Data</Translate>
                </Label>
                <AvField id="data-definition-defSampleData" type="text" name="defSampleData" />
              </AvGroup>
              <AvGroup>
                <Label for="data-definition-col">
                  <Translate contentKey="jhipsterApp.dataDefinition.col">Col</Translate>
                </Label>
                <AvInput id="data-definition-col" type="select" className="form-control" name="colId">
                  <option value="" key="0" />
                  {dsColumns
                    ? dsColumns.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="data-definition-type">
                  <Translate contentKey="jhipsterApp.dataDefinition.type">Type</Translate>
                </Label>
                <AvInput id="data-definition-type" type="select" className="form-control" name="typeId">
                  <option value="" key="0" />
                  {dsColumnTypes
                    ? dsColumnTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="data-definition-tbl">
                  <Translate contentKey="jhipsterApp.dataDefinition.tbl">Tbl</Translate>
                </Label>
                <AvInput id="data-definition-tbl" type="select" className="form-control" name="tblId">
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
              <Button tag={Link} id="cancel-save" to="/data-definition" replace color="info">
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
  dsColumns: storeState.dsColumns.entities,
  dsColumnTypes: storeState.dsColumnTypes.entities,
  dsTables: storeState.dsTables.entities,
  dataDefinitionEntity: storeState.dataDefinition.entity,
  loading: storeState.dataDefinition.loading,
  updating: storeState.dataDefinition.updating,
  updateSuccess: storeState.dataDefinition.updateSuccess
});

const mapDispatchToProps = {
  getDsColumns,
  getDsColumnTypes,
  getDsTables,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataDefinitionUpdate);
