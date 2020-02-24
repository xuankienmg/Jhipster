import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DpSourceColumns from './dp-source-columns';
import DpSourceColumnsDetail from './dp-source-columns-detail';
import DpSourceColumnsUpdate from './dp-source-columns-update';
import DpSourceColumnsDeleteDialog from './dp-source-columns-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DpSourceColumnsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DpSourceColumnsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DpSourceColumnsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DpSourceColumnsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DpSourceColumns} />
    </Switch>
  </>
);

export default Routes;
