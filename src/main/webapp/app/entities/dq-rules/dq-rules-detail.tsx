import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rules.reducer';
import { IDqRules } from 'app/shared/model/dq-rules.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRulesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRulesDetail = (props: IDqRulesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRulesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRules.detail.title">DqRules</Translate> [<b>{dqRulesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ruleName">
              <Translate contentKey="jhipsterApp.dqRules.ruleName">Rule Name</Translate>
            </span>
          </dt>
          <dd>{dqRulesEntity.ruleName}</dd>
          <dt>
            <span id="ruleDescription">
              <Translate contentKey="jhipsterApp.dqRules.ruleDescription">Rule Description</Translate>
            </span>
          </dt>
          <dd>{dqRulesEntity.ruleDescription}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqRules.type">Type</Translate>
          </dt>
          <dd>{dqRulesEntity.typeId ? dqRulesEntity.typeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqRules.risk">Risk</Translate>
          </dt>
          <dd>{dqRulesEntity.riskId ? dqRulesEntity.riskId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqRules.status">Status</Translate>
          </dt>
          <dd>{dqRulesEntity.statusId ? dqRulesEntity.statusId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqRules.cat">Cat</Translate>
          </dt>
          <dd>{dqRulesEntity.catId ? dqRulesEntity.catId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqRules.action">Action</Translate>
          </dt>
          <dd>{dqRulesEntity.actionId ? dqRulesEntity.actionId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dq-rules" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rules/${dqRulesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRules }: IRootState) => ({
  dqRulesEntity: dqRules.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRulesDetail);
