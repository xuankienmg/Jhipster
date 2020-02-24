import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDqRules } from 'app/shared/model/dq-rules.model';
import { getEntities as getDqRules } from 'app/entities/dq-rules/dq-rules.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dq-notifications.reducer';
import { IDqNotifications } from 'app/shared/model/dq-notifications.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqNotificationsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqNotificationsUpdate = (props: IDqNotificationsUpdateProps) => {
  const [ruleId, setRuleId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqNotificationsEntity, dqRules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-notifications' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...dqNotificationsEntity,
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
          <h2 id="jhipsterApp.dqNotifications.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqNotifications.home.createOrEditLabel">Create or edit a DqNotifications</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqNotificationsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-notifications-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-notifications-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="repicientIdLabel" for="dq-notifications-repicientId">
                  <Translate contentKey="jhipsterApp.dqNotifications.repicientId">Repicient Id</Translate>
                </Label>
                <AvField id="dq-notifications-repicientId" type="string" className="form-control" name="repicientId" />
              </AvGroup>
              <AvGroup>
                <Label id="repicientTypeIdLabel" for="dq-notifications-repicientTypeId">
                  <Translate contentKey="jhipsterApp.dqNotifications.repicientTypeId">Repicient Type Id</Translate>
                </Label>
                <AvField id="dq-notifications-repicientTypeId" type="string" className="form-control" name="repicientTypeId" />
              </AvGroup>
              <AvGroup>
                <Label for="dq-notifications-rule">
                  <Translate contentKey="jhipsterApp.dqNotifications.rule">Rule</Translate>
                </Label>
                <AvInput id="dq-notifications-rule" type="select" className="form-control" name="ruleId">
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
              <Button tag={Link} id="cancel-save" to="/dq-notifications" replace color="info">
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
  dqRules: storeState.dqRules.entities,
  dqNotificationsEntity: storeState.dqNotifications.entity,
  loading: storeState.dqNotifications.loading,
  updating: storeState.dqNotifications.updating,
  updateSuccess: storeState.dqNotifications.updateSuccess
});

const mapDispatchToProps = {
  getDqRules,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqNotificationsUpdate);
