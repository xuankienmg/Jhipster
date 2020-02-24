import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-standard-details-entity-int.reducer';
import { IDqStandardDetailsEntityInt } from 'app/shared/model/dq-standard-details-entity-int.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqStandardDetailsEntityIntDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityIntDetail = (props: IDqStandardDetailsEntityIntDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqStandardDetailsEntityIntEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.detail.title">DqStandardDetailsEntityInt</Translate> [
          <b>{dqStandardDetailsEntityIntEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="stdAttributeName">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.stdAttributeName">Std Attribute Name</Translate>
            </span>
          </dt>
          <dd>{dqStandardDetailsEntityIntEntity.stdAttributeName}</dd>
          <dt>
            <span id="stdAttributeValue">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.stdAttributeValue">Std Attribute Value</Translate>
            </span>
          </dt>
          <dd>{dqStandardDetailsEntityIntEntity.stdAttributeValue}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqStandardDetailsEntityInt.std">Std</Translate>
          </dt>
          <dd>{dqStandardDetailsEntityIntEntity.stdId ? dqStandardDetailsEntityIntEntity.stdId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dq-standard-details-entity-int" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-standard-details-entity-int/${dqStandardDetailsEntityIntEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityInt }: IRootState) => ({
  dqStandardDetailsEntityIntEntity: dqStandardDetailsEntityInt.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityIntDetail);
