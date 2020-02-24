import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './data-flows.reducer';
import { IDataFlows } from 'app/shared/model/data-flows.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDataFlowsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataFlowsDetail = (props: IDataFlowsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dataFlowsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dataFlows.detail.title">DataFlows</Translate> [<b>{dataFlowsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="flowName">
              <Translate contentKey="jhipsterApp.dataFlows.flowName">Flow Name</Translate>
            </span>
          </dt>
          <dd>{dataFlowsEntity.flowName}</dd>
          <dt>
            <span id="flowDescription">
              <Translate contentKey="jhipsterApp.dataFlows.flowDescription">Flow Description</Translate>
            </span>
          </dt>
          <dd>{dataFlowsEntity.flowDescription}</dd>
          <dt>
            <span id="source">
              <Translate contentKey="jhipsterApp.dataFlows.source">Source</Translate>
            </span>
          </dt>
          <dd>{dataFlowsEntity.source}</dd>
          <dt>
            <span id="destination">
              <Translate contentKey="jhipsterApp.dataFlows.destination">Destination</Translate>
            </span>
          </dt>
          <dd>{dataFlowsEntity.destination}</dd>
          <dt>
            <span id="transformation">
              <Translate contentKey="jhipsterApp.dataFlows.transformation">Transformation</Translate>
            </span>
          </dt>
          <dd>{dataFlowsEntity.transformation}</dd>
          <dt>
            <span id="lSET">
              <Translate contentKey="jhipsterApp.dataFlows.lSET">L SET</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={dataFlowsEntity.lSET} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="cET">
              <Translate contentKey="jhipsterApp.dataFlows.cET">C ET</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={dataFlowsEntity.cET} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataFlows.etlStatus">Etl Status</Translate>
          </dt>
          <dd>{dataFlowsEntity.etlStatusId ? dataFlowsEntity.etlStatusId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataFlows.etlPkg">Etl Pkg</Translate>
          </dt>
          <dd>{dataFlowsEntity.etlPkgId ? dataFlowsEntity.etlPkgId : ''}</dd>
        </dl>
        <Button tag={Link} to="/data-flows" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/data-flows/${dataFlowsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dataFlows }: IRootState) => ({
  dataFlowsEntity: dataFlows.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataFlowsDetail);
