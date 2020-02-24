import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEventCategories, defaultValue } from 'app/shared/model/event-categories.model';

export const ACTION_TYPES = {
  FETCH_EVENTCATEGORIES_LIST: 'eventCategories/FETCH_EVENTCATEGORIES_LIST',
  FETCH_EVENTCATEGORIES: 'eventCategories/FETCH_EVENTCATEGORIES',
  CREATE_EVENTCATEGORIES: 'eventCategories/CREATE_EVENTCATEGORIES',
  UPDATE_EVENTCATEGORIES: 'eventCategories/UPDATE_EVENTCATEGORIES',
  DELETE_EVENTCATEGORIES: 'eventCategories/DELETE_EVENTCATEGORIES',
  RESET: 'eventCategories/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEventCategories>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EventCategoriesState = Readonly<typeof initialState>;

// Reducer

export default (state: EventCategoriesState = initialState, action): EventCategoriesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EVENTCATEGORIES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EVENTCATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EVENTCATEGORIES):
    case REQUEST(ACTION_TYPES.UPDATE_EVENTCATEGORIES):
    case REQUEST(ACTION_TYPES.DELETE_EVENTCATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EVENTCATEGORIES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EVENTCATEGORIES):
    case FAILURE(ACTION_TYPES.CREATE_EVENTCATEGORIES):
    case FAILURE(ACTION_TYPES.UPDATE_EVENTCATEGORIES):
    case FAILURE(ACTION_TYPES.DELETE_EVENTCATEGORIES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTCATEGORIES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTCATEGORIES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EVENTCATEGORIES):
    case SUCCESS(ACTION_TYPES.UPDATE_EVENTCATEGORIES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EVENTCATEGORIES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/event-categories';

// Actions

export const getEntities: ICrudGetAllAction<IEventCategories> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTCATEGORIES_LIST,
    payload: axios.get<IEventCategories>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEventCategories> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTCATEGORIES,
    payload: axios.get<IEventCategories>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEventCategories> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EVENTCATEGORIES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEventCategories> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EVENTCATEGORIES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEventCategories> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EVENTCATEGORIES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
