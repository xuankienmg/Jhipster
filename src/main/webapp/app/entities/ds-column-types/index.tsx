import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsColumnTypes from './ds-column-types';
import DsColumnTypesDetail from './ds-column-types-detail';
import DsColumnTypesUpdate from './ds-column-types-update';
import DsColumnTypesDeleteDialog from './ds-column-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsColumnTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsColumnTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsColumnTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsColumnTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsColumnTypes} />
    </Switch>
  </>
);

export default Routes;
