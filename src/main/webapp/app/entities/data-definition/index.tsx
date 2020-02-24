import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DataDefinition from './data-definition';
import DataDefinitionDetail from './data-definition-detail';
import DataDefinitionUpdate from './data-definition-update';
import DataDefinitionDeleteDialog from './data-definition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DataDefinitionDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DataDefinitionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DataDefinitionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DataDefinitionDetail} />
      <ErrorBoundaryRoute path={match.url} component={DataDefinition} />
    </Switch>
  </>
);

export default Routes;
