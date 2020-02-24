import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardTypes from './dq-standard-types';
import DqStandardTypesDetail from './dq-standard-types-detail';
import DqStandardTypesUpdate from './dq-standard-types-update';
import DqStandardTypesDeleteDialog from './dq-standard-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardTypes} />
    </Switch>
  </>
);

export default Routes;
