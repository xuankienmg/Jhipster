import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDqStandards } from 'app/shared/model/dq-standards.model';
import { getEntities as getDqStandards } from 'app/entities/dq-standards/dq-standards.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dq-standard-details-entity-int.reducer';
import { IDqStandardDetailsEntityInt } from 'app/shared/model/dq-standard-details-entity-int.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqStandardDetailsEntityIntUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityIntUpdate = (props: IDqStandardDetailsEntityIntUpdateProps) => {
  const [stdId, setStdId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqStandardDetailsEntityIntEntity, dqStandards, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-standard-details-entity-int' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDqStandards();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dqStandardDetailsEntityIntEntity,
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
          <h2 id="jhipsterApp.dqStandardDetailsEntityInt.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.home.createOrEditLabel">
              Create or edit a DqStandardDetailsEntityInt
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqStandardDetailsEntityIntEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-standard-details-entity-int-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-standard-details-entity-int-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="stdAttributeNameLabel" for="dq-standard-details-entity-int-stdAttributeName">
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.stdAttributeName">Std Attribute Name</Translate>
                </Label>
                <AvField id="dq-standard-details-entity-int-stdAttributeName" type="text" name="stdAttributeName" />
              </AvGroup>
              <AvGroup>
                <Label id="stdAttributeValueLabel" for="dq-standard-details-entity-int-stdAttributeValue">
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.stdAttributeValue">Std Attribute Value</Translate>
                </Label>
                <AvField
                  id="dq-standard-details-entity-int-stdAttributeValue"
                  type="string"
                  className="form-control"
                  name="stdAttributeValue"
                />
              </AvGroup>
              <AvGroup>
                <Label for="dq-standard-details-entity-int-std">
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.std">Std</Translate>
                </Label>
                <AvInput id="dq-standard-details-entity-int-std" type="select" className="form-control" name="stdId">
                  <option value="" key="0" />
                  {dqStandards
                    ? dqStandards.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-standard-details-entity-int" replace color="info">
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
  dqStandards: storeState.dqStandards.entities,
  dqStandardDetailsEntityIntEntity: storeState.dqStandardDetailsEntityInt.entity,
  loading: storeState.dqStandardDetailsEntityInt.loading,
  updating: storeState.dqStandardDetailsEntityInt.updating,
  updateSuccess: storeState.dqStandardDetailsEntityInt.updateSuccess
});

const mapDispatchToProps = {
  getDqStandards,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityIntUpdate);
