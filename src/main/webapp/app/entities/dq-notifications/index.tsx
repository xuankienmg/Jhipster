import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqNotifications from './dq-notifications';
import DqNotificationsDetail from './dq-notifications-detail';
import DqNotificationsUpdate from './dq-notifications-update';
import DqNotificationsDeleteDialog from './dq-notifications-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqNotificationsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqNotificationsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqNotificationsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqNotificationsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqNotifications} />
    </Switch>
  </>
);

export default Routes;
