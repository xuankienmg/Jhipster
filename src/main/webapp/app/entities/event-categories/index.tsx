import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EventCategories from './event-categories';
import EventCategoriesDetail from './event-categories-detail';
import EventCategoriesUpdate from './event-categories-update';
import EventCategoriesDeleteDialog from './event-categories-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EventCategoriesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EventCategoriesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EventCategoriesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EventCategoriesDetail} />
      <ErrorBoundaryRoute path={match.url} component={EventCategories} />
    </Switch>
  </>
);

export default Routes;
