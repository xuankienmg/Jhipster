import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-stores.reducer';
import { IDsStores } from 'app/shared/model/ds-stores.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsStoresDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsStoresDetail = (props: IDsStoresDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsStoresEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsStores.detail.title">DsStores</Translate> [<b>{dsStoresEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="storeName">
              <Translate contentKey="jhipsterApp.dsStores.storeName">Store Name</Translate>
            </span>
          </dt>
          <dd>{dsStoresEntity.storeName}</dd>
          <dt>
            <span id="storeDescription">
              <Translate contentKey="jhipsterApp.dsStores.storeDescription">Store Description</Translate>
            </span>
          </dt>
          <dd>{dsStoresEntity.storeDescription}</dd>
          <dt>
            <span id="storeSize">
              <Translate contentKey="jhipsterApp.dsStores.storeSize">Store Size</Translate>
            </span>
          </dt>
          <dd>{dsStoresEntity.storeSize}</dd>
          <dt>
            <span id="growthSize">
              <Translate contentKey="jhipsterApp.dsStores.growthSize">Growth Size</Translate>
            </span>
          </dt>
          <dd>{dsStoresEntity.growthSize}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsStores.storeDbmsType">Store Dbms Type</Translate>
          </dt>
          <dd>{dsStoresEntity.storeDbmsTypeId ? dsStoresEntity.storeDbmsTypeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsStores.storeCollation">Store Collation</Translate>
          </dt>
          <dd>{dsStoresEntity.storeCollationId ? dsStoresEntity.storeCollationId : ''}</dd>
        </dl>
        <Button tag={Link} to="/ds-stores" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-stores/${dsStoresEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsStores }: IRootState) => ({
  dsStoresEntity: dsStores.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsStoresDetail);
