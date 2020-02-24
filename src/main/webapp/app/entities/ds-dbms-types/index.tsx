import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsDbmsTypes from './ds-dbms-types';
import DsDbmsTypesDetail from './ds-dbms-types-detail';
import DsDbmsTypesUpdate from './ds-dbms-types-update';
import DsDbmsTypesDeleteDialog from './ds-dbms-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsDbmsTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsDbmsTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsDbmsTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsDbmsTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsDbmsTypes} />
    </Switch>
  </>
);

export default Routes;
