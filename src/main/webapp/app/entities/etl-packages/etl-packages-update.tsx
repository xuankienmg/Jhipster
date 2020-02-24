import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etl-packages.reducer';
import { IEtlPackages } from 'app/shared/model/etl-packages.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtlPackagesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtlPackagesUpdate = (props: IEtlPackagesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { etlPackagesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/etl-packages' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...etlPackagesEntity,
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
          <h2 id="jhipsterApp.etlPackages.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.etlPackages.home.createOrEditLabel">Create or edit a EtlPackages</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : etlPackagesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="etl-packages-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="etl-packages-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="etlPkgNameLabel" for="etl-packages-etlPkgName">
                  <Translate contentKey="jhipsterApp.etlPackages.etlPkgName">Etl Pkg Name</Translate>
                </Label>
                <AvField id="etl-packages-etlPkgName" type="text" name="etlPkgName" />
              </AvGroup>
              <AvGroup>
                <Label id="etlPkgDescriptionLabel" for="etl-packages-etlPkgDescription">
                  <Translate contentKey="jhipsterApp.etlPackages.etlPkgDescription">Etl Pkg Description</Translate>
                </Label>
                <AvField id="etl-packages-etlPkgDescription" type="text" name="etlPkgDescription" />
              </AvGroup>
              <AvGroup>
                <Label id="etlPkgScheduleLabel" for="etl-packages-etlPkgSchedule">
                  <Translate contentKey="jhipsterApp.etlPackages.etlPkgSchedule">Etl Pkg Schedule</Translate>
                </Label>
                <AvField id="etl-packages-etlPkgSchedule" type="text" name="etlPkgSchedule" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/etl-packages" replace color="info">
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
  etlPackagesEntity: storeState.etlPackages.entity,
  loading: storeState.etlPackages.loading,
  updating: storeState.etlPackages.updating,
  updateSuccess: storeState.etlPackages.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtlPackagesUpdate);
