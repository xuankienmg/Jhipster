import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDqStandardTypes } from 'app/shared/model/dq-standard-types.model';
import { getEntities as getDqStandardTypes } from 'app/entities/dq-standard-types/dq-standard-types.reducer';
import { IDqRules } from 'app/shared/model/dq-rules.model';
import { getEntities as getDqRules } from 'app/entities/dq-rules/dq-rules.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dq-standards.reducer';
import { IDqStandards } from 'app/shared/model/dq-standards.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqStandardsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardsUpdate = (props: IDqStandardsUpdateProps) => {
  const [idsrule, setIdsrule] = useState([]);
  const [stdTypeId, setStdTypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqStandardsEntity, dqStandardTypes, dqRules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-standards' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDqStandardTypes();
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
        ...dqStandardsEntity,
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
          <h2 id="jhipsterApp.dqStandards.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqStandards.home.createOrEditLabel">Create or edit a DqStandards</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqStandardsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-standards-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-standards-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="stdNameLabel" for="dq-standards-stdName">
                  <Translate contentKey="jhipsterApp.dqStandards.stdName">Std Name</Translate>
                </Label>
                <AvField id="dq-standards-stdName" type="text" name="stdName" />
              </AvGroup>
              <AvGroup>
                <Label id="stdDescriptionLabel" for="dq-standards-stdDescription">
                  <Translate contentKey="jhipsterApp.dqStandards.stdDescription">Std Description</Translate>
                </Label>
                <AvField id="dq-standards-stdDescription" type="text" name="stdDescription" />
              </AvGroup>
              <AvGroup>
                <Label for="dq-standards-stdType">
                  <Translate contentKey="jhipsterApp.dqStandards.stdType">Std Type</Translate>
                </Label>
                <AvInput id="dq-standards-stdType" type="select" className="form-control" name="stdTypeId">
                  <option value="" key="0" />
                  {dqStandardTypes
                    ? dqStandardTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dq-standards-rule">
                  <Translate contentKey="jhipsterApp.dqStandards.rule">Rule</Translate>
                </Label>
                <AvInput
                  id="dq-standards-rule"
                  type="select"
                  multiple
                  className="form-control"
                  name="rules"
                  value={dqStandardsEntity.rules && dqStandardsEntity.rules.map(e => e.id)}
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
              <Button tag={Link} id="cancel-save" to="/dq-standards" replace color="info">
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
  dqStandardTypes: storeState.dqStandardTypes.entities,
  dqRules: storeState.dqRules.entities,
  dqStandardsEntity: storeState.dqStandards.entity,
  loading: storeState.dqStandards.loading,
  updating: storeState.dqStandards.updating,
  updateSuccess: storeState.dqStandards.updateSuccess
});

const mapDispatchToProps = {
  getDqStandardTypes,
  getDqRules,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardsUpdate);
