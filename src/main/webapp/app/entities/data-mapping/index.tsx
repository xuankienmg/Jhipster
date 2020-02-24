import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DataMapping from './data-mapping';
import DataMappingDetail from './data-mapping-detail';
import DataMappingUpdate from './data-mapping-update';
import DataMappingDeleteDialog from './data-mapping-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DataMappingDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DataMappingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DataMappingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DataMappingDetail} />
      <ErrorBoundaryRoute path={match.url} component={DataMapping} />
    </Switch>
  </>
);

export default Routes;
