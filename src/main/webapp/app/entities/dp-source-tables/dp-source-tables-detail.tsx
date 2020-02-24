import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dp-source-tables.reducer';
import { IDpSourceTables } from 'app/shared/model/dp-source-tables.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDpSourceTablesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpSourceTablesDetail = (props: IDpSourceTablesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dpSourceTablesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dpSourceTables.detail.title">DpSourceTables</Translate> [<b>{dpSourceTablesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="rows">
              <Translate contentKey="jhipsterApp.dpSourceTables.rows">Rows</Translate>
            </span>
          </dt>
          <dd>{dpSourceTablesEntity.rows}</dd>
          <dt>
            <span id="rowSize">
              <Translate contentKey="jhipsterApp.dpSourceTables.rowSize">Row Size</Translate>
            </span>
          </dt>
          <dd>{dpSourceTablesEntity.rowSize}</dd>
          <dt>
            <span id="columns">
              <Translate contentKey="jhipsterApp.dpSourceTables.columns">Columns</Translate>
            </span>
          </dt>
          <dd>{dpSourceTablesEntity.columns}</dd>
          <dt>
            <span id="hasTimestamp">
              <Translate contentKey="jhipsterApp.dpSourceTables.hasTimestamp">Has Timestamp</Translate>
            </span>
          </dt>
          <dd>{dpSourceTablesEntity.hasTimestamp ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dpSourceTables.tbl">Tbl</Translate>
          </dt>
          <dd>{dpSourceTablesEntity.tblId ? dpSourceTablesEntity.tblId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dp-source-tables" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dp-source-tables/${dpSourceTablesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dpSourceTables }: IRootState) => ({
  dpSourceTablesEntity: dpSourceTables.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpSourceTablesDetail);
