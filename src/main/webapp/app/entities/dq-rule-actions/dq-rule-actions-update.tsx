import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './dq-rule-actions.reducer';
import { IDqRuleActions } from 'app/shared/model/dq-rule-actions.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqRuleActionsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleActionsUpdate = (props: IDqRuleActionsUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqRuleActionsEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-rule-actions' + props.location.search);
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
        ...dqRuleActionsEntity,
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
          <h2 id="jhipsterApp.dqRuleActions.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqRuleActions.home.createOrEditLabel">Create or edit a DqRuleActions</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqRuleActionsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-rule-actions-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-rule-actions-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="actionNameLabel" for="dq-rule-actions-actionName">
                  <Translate contentKey="jhipsterApp.dqRuleActions.actionName">Action Name</Translate>
                </Label>
                <AvField id="dq-rule-actions-actionName" type="text" name="actionName" />
              </AvGroup>
              <AvGroup>
                <Label id="actionDescriptionLabel" for="dq-rule-actions-actionDescription">
                  <Translate contentKey="jhipsterApp.dqRuleActions.actionDescription">Action Description</Translate>
                </Label>
                <AvField id="dq-rule-actions-actionDescription" type="text" name="actionDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-rule-actions" replace color="info">
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
  dqRuleActionsEntity: storeState.dqRuleActions.entity,
  loading: storeState.dqRuleActions.loading,
  updating: storeState.dqRuleActions.updating,
  updateSuccess: storeState.dqRuleActions.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleActionsUpdate);
