import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-standard-details-entity-datetime.reducer';
import { IDqStandardDetailsEntityDatetime } from 'app/shared/model/dq-standard-details-entity-datetime.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqStandardDetailsEntityDatetimeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityDatetimeDetail = (props: IDqStandardDetailsEntityDatetimeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqStandardDetailsEntityDatetimeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqStandardDetailsEntityDatetime.detail.title">DqStandardDetailsEntityDatetime</Translate> [
          <b>{dqStandardDetailsEntityDatetimeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="stdAttributeName">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityDatetime.stdAttributeName">Std Attribute Name</Translate>
            </span>
          </dt>
          <dd>{dqStandardDetailsEntityDatetimeEntity.stdAttributeName}</dd>
          <dt>
            <span id="stdAttributeValue">
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityDatetime.stdAttributeValue">Std Attribute Value</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={dqStandardDetailsEntityDatetimeEntity.stdAttributeValue} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqStandardDetailsEntityDatetime.std">Std</Translate>
          </dt>
          <dd>{dqStandardDetailsEntityDatetimeEntity.stdId ? dqStandardDetailsEntityDatetimeEntity.stdId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dq-standard-details-entity-datetime" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/dq-standard-details-entity-datetime/${dqStandardDetailsEntityDatetimeEntity.id}/edit`}
          replace
          color="primary"
        >
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityDatetime }: IRootState) => ({
  dqStandardDetailsEntityDatetimeEntity: dqStandardDetailsEntityDatetime.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityDatetimeDetail);
