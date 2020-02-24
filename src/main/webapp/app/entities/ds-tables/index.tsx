import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsTables from './ds-tables';
import DsTablesDetail from './ds-tables-detail';
import DsTablesUpdate from './ds-tables-update';
import DsTablesDeleteDialog from './ds-tables-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsTablesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsTablesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsTablesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsTablesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsTables} />
    </Switch>
  </>
);

export default Routes;
