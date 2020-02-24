import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rule-actions.reducer';
import { IDqRuleActions } from 'app/shared/model/dq-rule-actions.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRuleActionsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleActionsDetail = (props: IDqRuleActionsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRuleActionsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRuleActions.detail.title">DqRuleActions</Translate> [<b>{dqRuleActionsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="actionName">
              <Translate contentKey="jhipsterApp.dqRuleActions.actionName">Action Name</Translate>
            </span>
          </dt>
          <dd>{dqRuleActionsEntity.actionName}</dd>
          <dt>
            <span id="actionDescription">
              <Translate contentKey="jhipsterApp.dqRuleActions.actionDescription">Action Description</Translate>
            </span>
          </dt>
          <dd>{dqRuleActionsEntity.actionDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-rule-actions" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rule-actions/${dqRuleActionsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRuleActions }: IRootState) => ({
  dqRuleActionsEntity: dqRuleActions.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleActionsDetail);
