import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDqRuleTypes } from 'app/shared/model/dq-rule-types.model';
import { getEntities as getDqRuleTypes } from 'app/entities/dq-rule-types/dq-rule-types.reducer';
import { IDqRuleRiskLevels } from 'app/shared/model/dq-rule-risk-levels.model';
import { getEntities as getDqRuleRiskLevels } from 'app/entities/dq-rule-risk-levels/dq-rule-risk-levels.reducer';
import { IDqRuleStatus } from 'app/shared/model/dq-rule-status.model';
import { getEntities as getDqRuleStatuses } from 'app/entities/dq-rule-status/dq-rule-status.reducer';
import { IDqRuleCategories } from 'app/shared/model/dq-rule-categories.model';
import { getEntities as getDqRuleCategories } from 'app/entities/dq-rule-categories/dq-rule-categories.reducer';
import { IDqRuleActions } from 'app/shared/model/dq-rule-actions.model';
import { getEntities as getDqRuleActions } from 'app/entities/dq-rule-actions/dq-rule-actions.reducer';
import { IDqStandards } from 'app/shared/model/dq-standards.model';
import { getEntities as getDqStandards } from 'app/entities/dq-standards/dq-standards.reducer';
import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { getEntities as getDsColumns } from 'app/entities/ds-columns/ds-columns.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dq-rules.reducer';
import { IDqRules } from 'app/shared/model/dq-rules.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqRulesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRulesUpdate = (props: IDqRulesUpdateProps) => {
  const [typeId, setTypeId] = useState('0');
  const [riskId, setRiskId] = useState('0');
  const [statusId, setStatusId] = useState('0');
  const [catId, setCatId] = useState('0');
  const [actionId, setActionId] = useState('0');
  const [stdId, setStdId] = useState('0');
  const [colId, setColId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const {
    dqRulesEntity,
    dqRuleTypes,
    dqRuleRiskLevels,
    dqRuleStatuses,
    dqRuleCategories,
    dqRuleActions,
    dqStandards,
    dsColumns,
    loading,
    updating
  } = props;

  const handleClose = () => {
    props.history.push('/dq-rules' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDqRuleTypes();
    props.getDqRuleRiskLevels();
    props.getDqRuleStatuses();
    props.getDqRuleCategories();
    props.getDqRuleActions();
    props.getDqStandards();
    props.getDsColumns();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dqRulesEntity,
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
          <h2 id="jhipsterApp.dqRules.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqRules.home.createOrEditLabel">Create or edit a DqRules</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqRulesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-rules-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-rules-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="ruleNameLabel" for="dq-rules-ruleName">
                  <Translate contentKey="jhipsterApp.dqRules.ruleName">Rule Name</Translate>
                </Label>
                <AvField id="dq-rules-ruleName" type="text" name="ruleName" />
              </AvGroup>
              <AvGroup>
                <Label id="ruleDescriptionLabel" for="dq-rules-ruleDescription">
                  <Translate contentKey="jhipsterApp.dqRules.ruleDescription">Rule Description</Translate>
                </Label>
                <AvField id="dq-rules-ruleDescription" type="text" name="ruleDescription" />
              </AvGroup>
              <AvGroup>
                <Label for="dq-rules-type">
                  <Translate contentKey="jhipsterApp.dqRules.type">Type</Translate>
                </Label>
                <AvInput id="dq-rules-type" type="select" className="form-control" name="typeId">
                  <option value="" key="0" />
                  {dqRuleTypes
                    ? dqRuleTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dq-rules-risk">
                  <Translate contentKey="jhipsterApp.dqRules.risk">Risk</Translate>
                </Label>
                <AvInput id="dq-rules-risk" type="select" className="form-control" name="riskId">
                  <option value="" key="0" />
                  {dqRuleRiskLevels
                    ? dqRuleRiskLevels.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dq-rules-status">
                  <Translate contentKey="jhipsterApp.dqRules.status">Status</Translate>
                </Label>
                <AvInput id="dq-rules-status" type="select" className="form-control" name="statusId">
                  <option value="" key="0" />
                  {dqRuleStatuses
                    ? dqRuleStatuses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dq-rules-cat">
                  <Translate contentKey="jhipsterApp.dqRules.cat">Cat</Translate>
                </Label>
                <AvInput id="dq-rules-cat" type="select" className="form-control" name="catId">
                  <option value="" key="0" />
                  {dqRuleCategories
                    ? dqRuleCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dq-rules-action">
                  <Translate contentKey="jhipsterApp.dqRules.action">Action</Translate>
                </Label>
                <AvInput id="dq-rules-action" type="select" className="form-control" name="actionId">
                  <option value="" key="0" />
                  {dqRuleActions
                    ? dqRuleActions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-rules" replace color="info">
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
  dqRuleTypes: storeState.dqRuleTypes.entities,
  dqRuleRiskLevels: storeState.dqRuleRiskLevels.entities,
  dqRuleStatuses: storeState.dqRuleStatus.entities,
  dqRuleCategories: storeState.dqRuleCategories.entities,
  dqRuleActions: storeState.dqRuleActions.entities,
  dqStandards: storeState.dqStandards.entities,
  dsColumns: storeState.dsColumns.entities,
  dqRulesEntity: storeState.dqRules.entity,
  loading: storeState.dqRules.loading,
  updating: storeState.dqRules.updating,
  updateSuccess: storeState.dqRules.updateSuccess
});

const mapDispatchToProps = {
  getDqRuleTypes,
  getDqRuleRiskLevels,
  getDqRuleStatuses,
  getDqRuleCategories,
  getDqRuleActions,
  getDqStandards,
  getDsColumns,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRulesUpdate);
