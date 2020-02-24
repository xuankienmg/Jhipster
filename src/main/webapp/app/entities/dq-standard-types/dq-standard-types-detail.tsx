import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-standard-types.reducer';
import { IDqStandardTypes } from 'app/shared/model/dq-standard-types.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqStandardTypesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardTypesDetail = (props: IDqStandardTypesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqStandardTypesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqStandardTypes.detail.title">DqStandardTypes</Translate> [<b>{dqStandardTypesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="stdTypeName">
              <Translate contentKey="jhipsterApp.dqStandardTypes.stdTypeName">Std Type Name</Translate>
            </span>
          </dt>
          <dd>{dqStandardTypesEntity.stdTypeName}</dd>
          <dt>
            <span id="stdTypeDescription">
              <Translate contentKey="jhipsterApp.dqStandardTypes.stdTypeDescription">Std Type Description</Translate>
            </span>
          </dt>
          <dd>{dqStandardTypesEntity.stdTypeDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-standard-types" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-standard-types/${dqStandardTypesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqStandardTypes }: IRootState) => ({
  dqStandardTypesEntity: dqStandardTypes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardTypesDetail);
