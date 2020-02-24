import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rule-risk-levels.reducer';
import { IDqRuleRiskLevels } from 'app/shared/model/dq-rule-risk-levels.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRuleRiskLevelsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleRiskLevelsDetail = (props: IDqRuleRiskLevelsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRuleRiskLevelsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRuleRiskLevels.detail.title">DqRuleRiskLevels</Translate> [<b>{dqRuleRiskLevelsEntity.id}</b>
          ]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="riskName">
              <Translate contentKey="jhipsterApp.dqRuleRiskLevels.riskName">Risk Name</Translate>
            </span>
          </dt>
          <dd>{dqRuleRiskLevelsEntity.riskName}</dd>
          <dt>
            <span id="riskDescription">
              <Translate contentKey="jhipsterApp.dqRuleRiskLevels.riskDescription">Risk Description</Translate>
            </span>
          </dt>
          <dd>{dqRuleRiskLevelsEntity.riskDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-rule-risk-levels" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rule-risk-levels/${dqRuleRiskLevelsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRuleRiskLevels }: IRootState) => ({
  dqRuleRiskLevelsEntity: dqRuleRiskLevels.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleRiskLevelsDetail);
