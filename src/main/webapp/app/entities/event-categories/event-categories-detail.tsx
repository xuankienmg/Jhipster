import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './event-categories.reducer';
import { IEventCategories } from 'app/shared/model/event-categories.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventCategoriesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EventCategoriesDetail = (props: IEventCategoriesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { eventCategoriesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.eventCategories.detail.title">EventCategories</Translate> [<b>{eventCategoriesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="eventCatName">
              <Translate contentKey="jhipsterApp.eventCategories.eventCatName">Event Cat Name</Translate>
            </span>
          </dt>
          <dd>{eventCategoriesEntity.eventCatName}</dd>
        </dl>
        <Button tag={Link} to="/event-categories" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/event-categories/${eventCategoriesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ eventCategories }: IRootState) => ({
  eventCategoriesEntity: eventCategories.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EventCategoriesDetail);
