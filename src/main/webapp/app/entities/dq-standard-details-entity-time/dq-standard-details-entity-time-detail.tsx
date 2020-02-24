import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-standard-details-entity-time.reducer';
import { IDqStandardDetailsEntityTime } from 'app/shared/model/dq-standard-details-entity-time.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqStandardDetailsEntityTimeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityTimeDetail = (props: IDqStandardDetailsEntityTimeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqStandardDetailsEntityTimeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqStandardDetailsEntityTime.detail.title">DqStandardDetailsEntityTime</Translate> [
          <b>{dqStandardDetailsEntityTimeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="stdAttributeName">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityTime.stdAttributeName">Std Attribute Name</Translate>
            </span>
          </dt>
          <dd>{dqStandardDetailsEntityTimeEntity.stdAttributeName}</dd>
          <dt>
            <span id="stdAttributeValue">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityTime.stdAttributeValue">Std Attribute Value</Translate>
            </span>
          </dt>
          <dd>{dqStandardDetailsEntityTimeEntity.stdAttributeValue}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqStandardDetailsEntityTime.std">Std</Translate>
          </dt>
          <dd>{dqStandardDetailsEntityTimeEntity.stdId ? dqStandardDetailsEntityTimeEntity.stdId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dq-standard-details-entity-time" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-standard-details-entity-time/${dqStandardDetailsEntityTimeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityTime }: IRootState) => ({
  dqStandardDetailsEntityTimeEntity: dqStandardDetailsEntityTime.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityTimeDetail);
