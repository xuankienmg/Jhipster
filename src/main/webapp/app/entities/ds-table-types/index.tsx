import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsTableTypes from './ds-table-types';
import DsTableTypesDetail from './ds-table-types-detail';
import DsTableTypesUpdate from './ds-table-types-update';
import DsTableTypesDeleteDialog from './ds-table-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsTableTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsTableTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsTableTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsTableTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsTableTypes} />
    </Switch>
  </>
);

export default Routes;
