import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-tables.reducer';
import { IDsTables } from 'app/shared/model/ds-tables.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsTablesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsTablesDetail = (props: IDsTablesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsTablesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsTables.detail.title">DsTables</Translate> [<b>{dsTablesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tblName">
              <Translate contentKey="jhipsterApp.dsTables.tblName">Tbl Name</Translate>
            </span>
          </dt>
          <dd>{dsTablesEntity.tblName}</dd>
          <dt>
            <span id="tblDescription">
              <Translate contentKey="jhipsterApp.dsTables.tblDescription">Tbl Description</Translate>
            </span>
          </dt>
          <dd>{dsTablesEntity.tblDescription}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsTables.tblType">Tbl Type</Translate>
          </dt>
          <dd>{dsTablesEntity.tblTypeId ? dsTablesEntity.tblTypeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsTables.store">Store</Translate>
          </dt>
          <dd>{dsTablesEntity.storeId ? dsTablesEntity.storeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/ds-tables" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-tables/${dsTablesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsTables }: IRootState) => ({
  dsTablesEntity: dsTables.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsTablesDetail);
