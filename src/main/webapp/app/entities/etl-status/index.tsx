import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtlStatus from './etl-status';
import EtlStatusDetail from './etl-status-detail';
import EtlStatusUpdate from './etl-status-update';
import EtlStatusDeleteDialog from './etl-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtlStatusDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtlStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtlStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtlStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtlStatus} />
    </Switch>
  </>
);

export default Routes;
