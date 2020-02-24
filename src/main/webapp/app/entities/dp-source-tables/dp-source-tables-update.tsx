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
import { getEntity, updateEntity, createEntity, reset } from './dp-source-tables.reducer';
import { IDpSourceTables } from 'app/shared/model/dp-source-tables.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDpSourceTablesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpSourceTablesUpdate = (props: IDpSourceTablesUpdateProps) => {
  const [tblId, setTblId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dpSourceTablesEntity, dsTables, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dp-source-tables' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...dpSourceTablesEntity,
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
          <h2 id="jhipsterApp.dpSourceTables.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dpSourceTables.home.createOrEditLabel">Create or edit a DpSourceTables</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dpSourceTablesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dp-source-tables-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dp-source-tables-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="rowsLabel" for="dp-source-tables-rows">
                  <Translate contentKey="jhipsterApp.dpSourceTables.rows">Rows</Translate>
                </Label>
                <AvField id="dp-source-tables-rows" type="string" className="form-control" name="rows" />
              </AvGroup>
              <AvGroup>
                <Label id="rowSizeLabel" for="dp-source-tables-rowSize">
                  <Translate contentKey="jhipsterApp.dpSourceTables.rowSize">Row Size</Translate>
                </Label>
                <AvField id="dp-source-tables-rowSize" type="string" className="form-control" name="rowSize" />
              </AvGroup>
              <AvGroup>
                <Label id="columnsLabel" for="dp-source-tables-columns">
                  <Translate contentKey="jhipsterApp.dpSourceTables.columns">Columns</Translate>
                </Label>
                <AvField id="dp-source-tables-columns" type="string" className="form-control" name="columns" />
              </AvGroup>
              <AvGroup check>
                <Label id="hasTimestampLabel">
                  <AvInput id="dp-source-tables-hasTimestamp" type="checkbox" className="form-check-input" name="hasTimestamp" />
                  <Translate contentKey="jhipsterApp.dpSourceTables.hasTimestamp">Has Timestamp</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="dp-source-tables-tbl">
                  <Translate contentKey="jhipsterApp.dpSourceTables.tbl">Tbl</Translate>
                </Label>
                <AvInput id="dp-source-tables-tbl" type="select" className="form-control" name="tblId">
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
              <Button tag={Link} id="cancel-save" to="/dp-source-tables" replace color="info">
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
  dpSourceTablesEntity: storeState.dpSourceTables.entity,
  loading: storeState.dpSourceTables.loading,
  updating: storeState.dpSourceTables.updating,
  updateSuccess: storeState.dpSourceTables.updateSuccess
});

const mapDispatchToProps = {
  getDsTables,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpSourceTablesUpdate);
