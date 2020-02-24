import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DataFlows from './data-flows';
import DataFlowsDetail from './data-flows-detail';
import DataFlowsUpdate from './data-flows-update';
import DataFlowsDeleteDialog from './data-flows-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DataFlowsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DataFlowsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DataFlowsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DataFlowsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DataFlows} />
    </Switch>
  </>
);

export default Routes;
