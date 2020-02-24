import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityTime from './dq-standard-details-entity-time';
import DqStandardDetailsEntityTimeDetail from './dq-standard-details-entity-time-detail';
import DqStandardDetailsEntityTimeUpdate from './dq-standard-details-entity-time-update';
import DqStandardDetailsEntityTimeDeleteDialog from './dq-standard-details-entity-time-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityTimeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityTimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityTimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityTimeDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityTime} />
    </Switch>
  </>
);

export default Routes;
