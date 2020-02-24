import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DpSourceTables from './dp-source-tables';
import DpSourceTablesDetail from './dp-source-tables-detail';
import DpSourceTablesUpdate from './dp-source-tables-update';
import DpSourceTablesDeleteDialog from './dp-source-tables-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DpSourceTablesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DpSourceTablesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DpSourceTablesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DpSourceTablesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DpSourceTables} />
    </Switch>
  </>
);

export default Routes;
