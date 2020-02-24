import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtlStatus } from 'app/shared/model/etl-status.model';
import { getEntities as getEtlStatuses } from 'app/entities/etl-status/etl-status.reducer';
import { IEtlPackages } from 'app/shared/model/etl-packages.model';
import { getEntities as getEtlPackages } from 'app/entities/etl-packages/etl-packages.reducer';
import { getEntity, updateEntity, createEntity, reset } from './data-flows.reducer';
import { IDataFlows } from 'app/shared/model/data-flows.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDataFlowsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataFlowsUpdate = (props: IDataFlowsUpdateProps) => {
  const [etlStatusId, setEtlStatusId] = useState('0');
  const [etlPkgId, setEtlPkgId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dataFlowsEntity, etlStatuses, etlPackages, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/data-flows' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEtlStatuses();
    props.getEtlPackages();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.lSET = convertDateTimeToServer(values.lSET);
    values.cET = convertDateTimeToServer(values.cET);

    if (errors.length === 0) {
      const entity = {
        ...dataFlowsEntity,
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
          <h2 id="jhipsterApp.dataFlows.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dataFlows.home.createOrEditLabel">Create or edit a DataFlows</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dataFlowsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="data-flows-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="data-flows-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="flowNameLabel" for="data-flows-flowName">
                  <Translate contentKey="jhipsterApp.dataFlows.flowName">Flow Name</Translate>
                </Label>
                <AvField id="data-flows-flowName" type="text" name="flowName" />
              </AvGroup>
              <AvGroup>
                <Label id="flowDescriptionLabel" for="data-flows-flowDescription">
                  <Translate contentKey="jhipsterApp.dataFlows.flowDescription">Flow Description</Translate>
                </Label>
                <AvField id="data-flows-flowDescription" type="text" name="flowDescription" />
              </AvGroup>
              <AvGroup>
                <Label id="sourceLabel" for="data-flows-source">
                  <Translate contentKey="jhipsterApp.dataFlows.source">Source</Translate>
                </Label>
                <AvField id="data-flows-source" type="text" name="source" />
              </AvGroup>
              <AvGroup>
                <Label id="destinationLabel" for="data-flows-destination">
                  <Translate contentKey="jhipsterApp.dataFlows.destination">Destination</Translate>
                </Label>
                <AvField id="data-flows-destination" type="text" name="destination" />
              </AvGroup>
              <AvGroup>
                <Label id="transformationLabel" for="data-flows-transformation">
                  <Translate contentKey="jhipsterApp.dataFlows.transformation">Transformation</Translate>
                </Label>
                <AvField id="data-flows-transformation" type="text" name="transformation" />
              </AvGroup>
              <AvGroup>
                <Label id="lSETLabel" for="data-flows-lSET">
                  <Translate contentKey="jhipsterApp.dataFlows.lSET">L SET</Translate>
                </Label>
                <AvInput
                  id="data-flows-lSET"
                  type="datetime-local"
                  className="form-control"
                  name="lSET"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.dataFlowsEntity.lSET)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="cETLabel" for="data-flows-cET">
                  <Translate contentKey="jhipsterApp.dataFlows.cET">C ET</Translate>
                </Label>
                <AvInput
                  id="data-flows-cET"
                  type="datetime-local"
                  className="form-control"
                  name="cET"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.dataFlowsEntity.cET)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="data-flows-etlStatus">
                  <Translate contentKey="jhipsterApp.dataFlows.etlStatus">Etl Status</Translate>
                </Label>
                <AvInput id="data-flows-etlStatus" type="select" className="form-control" name="etlStatusId">
                  <option value="" key="0" />
                  {etlStatuses
                    ? etlStatuses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="data-flows-etlPkg">
                  <Translate contentKey="jhipsterApp.dataFlows.etlPkg">Etl Pkg</Translate>
                </Label>
                <AvInput id="data-flows-etlPkg" type="select" className="form-control" name="etlPkgId">
                  <option value="" key="0" />
                  {etlPackages
                    ? etlPackages.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/data-flows" replace color="info">
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
  etlStatuses: storeState.etlStatus.entities,
  etlPackages: storeState.etlPackages.entities,
  dataFlowsEntity: storeState.dataFlows.entity,
  loading: storeState.dataFlows.loading,
  updating: storeState.dataFlows.updating,
  updateSuccess: storeState.dataFlows.updateSuccess
});

const mapDispatchToProps = {
  getEtlStatuses,
  getEtlPackages,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataFlowsUpdate);
