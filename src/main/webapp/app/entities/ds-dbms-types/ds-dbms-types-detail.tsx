import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-dbms-types.reducer';
import { IDsDbmsTypes } from 'app/shared/model/ds-dbms-types.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsDbmsTypesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsDbmsTypesDetail = (props: IDsDbmsTypesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsDbmsTypesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsDbmsTypes.detail.title">DsDbmsTypes</Translate> [<b>{dsDbmsTypesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dbmsTypeName">
              <Translate contentKey="jhipsterApp.dsDbmsTypes.dbmsTypeName">Dbms Type Name</Translate>
            </span>
          </dt>
          <dd>{dsDbmsTypesEntity.dbmsTypeName}</dd>
          <dt>
            <span id="dbsmTypeDescription">
              <Translate contentKey="jhipsterApp.dsDbmsTypes.dbsmTypeDescription">Dbsm Type Description</Translate>
            </span>
          </dt>
          <dd>{dsDbmsTypesEntity.dbsmTypeDescription}</dd>
        </dl>
        <Button tag={Link} to="/ds-dbms-types" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-dbms-types/${dsDbmsTypesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsDbmsTypes }: IRootState) => ({
  dsDbmsTypesEntity: dsDbmsTypes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsDbmsTypesDetail);
