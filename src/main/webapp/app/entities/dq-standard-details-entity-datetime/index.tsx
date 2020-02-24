import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityDatetime from './dq-standard-details-entity-datetime';
import DqStandardDetailsEntityDatetimeDetail from './dq-standard-details-entity-datetime-detail';
import DqStandardDetailsEntityDatetimeUpdate from './dq-standard-details-entity-datetime-update';
import DqStandardDetailsEntityDatetimeDeleteDialog from './dq-standard-details-entity-datetime-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityDatetimeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityDatetimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityDatetimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityDatetimeDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityDatetime} />
    </Switch>
  </>
);

export default Routes;
