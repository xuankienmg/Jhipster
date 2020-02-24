import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-collations.reducer';
import { IDsCollations } from 'app/shared/model/ds-collations.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsCollationsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsCollationsDetail = (props: IDsCollationsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsCollationsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsCollations.detail.title">DsCollations</Translate> [<b>{dsCollationsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="collationName">
              <Translate contentKey="jhipsterApp.dsCollations.collationName">Collation Name</Translate>
            </span>
          </dt>
          <dd>{dsCollationsEntity.collationName}</dd>
          <dt>
            <span id="collationDescription">
              <Translate contentKey="jhipsterApp.dsCollations.collationDescription">Collation Description</Translate>
            </span>
          </dt>
          <dd>{dsCollationsEntity.collationDescription}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsCollations.dbmsType">Dbms Type</Translate>
          </dt>
          <dd>{dsCollationsEntity.dbmsTypeId ? dsCollationsEntity.dbmsTypeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/ds-collations" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-collations/${dsCollationsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsCollations }: IRootState) => ({
  dsCollationsEntity: dsCollations.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsCollationsDetail);
