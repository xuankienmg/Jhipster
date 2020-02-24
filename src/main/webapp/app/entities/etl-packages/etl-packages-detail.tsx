import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etl-packages.reducer';
import { IEtlPackages } from 'app/shared/model/etl-packages.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtlPackagesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtlPackagesDetail = (props: IEtlPackagesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { etlPackagesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.etlPackages.detail.title">EtlPackages</Translate> [<b>{etlPackagesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="etlPkgName">
              <Translate contentKey="jhipsterApp.etlPackages.etlPkgName">Etl Pkg Name</Translate>
            </span>
          </dt>
          <dd>{etlPackagesEntity.etlPkgName}</dd>
          <dt>
            <span id="etlPkgDescription">
              <Translate contentKey="jhipsterApp.etlPackages.etlPkgDescription">Etl Pkg Description</Translate>
            </span>
          </dt>
          <dd>{etlPackagesEntity.etlPkgDescription}</dd>
          <dt>
            <span id="etlPkgSchedule">
              <Translate contentKey="jhipsterApp.etlPackages.etlPkgSchedule">Etl Pkg Schedule</Translate>
            </span>
          </dt>
          <dd>{etlPackagesEntity.etlPkgSchedule}</dd>
        </dl>
        <Button tag={Link} to="/etl-packages" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etl-packages/${etlPackagesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ etlPackages }: IRootState) => ({
  etlPackagesEntity: etlPackages.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtlPackagesDetail);
