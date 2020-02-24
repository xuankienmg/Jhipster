import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EventTypes from './event-types';
import EventTypesDetail from './event-types-detail';
import EventTypesUpdate from './event-types-update';
import EventTypesDeleteDialog from './event-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EventTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EventTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EventTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EventTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={EventTypes} />
    </Switch>
  </>
);

export default Routes;
