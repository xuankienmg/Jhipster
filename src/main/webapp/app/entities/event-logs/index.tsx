import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EventLogs from './event-logs';
import EventLogsDetail from './event-logs-detail';
import EventLogsUpdate from './event-logs-update';
import EventLogsDeleteDialog from './event-logs-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EventLogsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EventLogsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EventLogsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EventLogsDetail} />
      <ErrorBoundaryRoute path={match.url} component={EventLogs} />
    </Switch>
  </>
);

export default Routes;
