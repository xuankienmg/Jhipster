import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-standards.reducer';
import { IDqStandards } from 'app/shared/model/dq-standards.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqStandardsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardsDetail = (props: IDqStandardsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqStandardsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqStandards.detail.title">DqStandards</Translate> [<b>{dqStandardsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="stdName">
              <Translate contentKey="jhipsterApp.dqStandards.stdName">Std Name</Translate>
            </span>
          </dt>
          <dd>{dqStandardsEntity.stdName}</dd>
          <dt>
            <span id="stdDescription">
              <Translate contentKey="jhipsterApp.dqStandards.stdDescription">Std Description</Translate>
            </span>
          </dt>
          <dd>{dqStandardsEntity.stdDescription}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqStandards.stdType">Std Type</Translate>
          </dt>
          <dd>{dqStandardsEntity.stdTypeId ? dqStandardsEntity.stdTypeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqStandards.rule">Rule</Translate>
          </dt>
          <dd>
            {dqStandardsEntity.rules
              ? dqStandardsEntity.rules.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === dqStandardsEntity.rules.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/dq-standards" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-standards/${dqStandardsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqStandards }: IRootState) => ({
  dqStandardsEntity: dqStandards.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardsDetail);
