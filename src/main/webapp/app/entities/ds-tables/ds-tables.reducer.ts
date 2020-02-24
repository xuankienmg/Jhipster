import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsTables, defaultValue } from 'app/shared/model/ds-tables.model';

export const ACTION_TYPES = {
  FETCH_DSTABLES_LIST: 'dsTables/FETCH_DSTABLES_LIST',
  FETCH_DSTABLES: 'dsTables/FETCH_DSTABLES',
  CREATE_DSTABLES: 'dsTables/CREATE_DSTABLES',
  UPDATE_DSTABLES: 'dsTables/UPDATE_DSTABLES',
  DELETE_DSTABLES: 'dsTables/DELETE_DSTABLES',
  RESET: 'dsTables/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsTables>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsTablesState = Readonly<typeof initialState>;

// Reducer

export default (state: DsTablesState = initialState, action): DsTablesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSTABLES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSTABLES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSTABLES):
    case REQUEST(ACTION_TYPES.UPDATE_DSTABLES):
    case REQUEST(ACTION_TYPES.DELETE_DSTABLES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSTABLES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSTABLES):
    case FAILURE(ACTION_TYPES.CREATE_DSTABLES):
    case FAILURE(ACTION_TYPES.UPDATE_DSTABLES):
    case FAILURE(ACTION_TYPES.DELETE_DSTABLES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSTABLES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSTABLES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSTABLES):
    case SUCCESS(ACTION_TYPES.UPDATE_DSTABLES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSTABLES):
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

const apiUrl = 'api/ds-tables';

// Actions

export const getEntities: ICrudGetAllAction<IDsTables> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSTABLES_LIST,
    payload: axios.get<IDsTables>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsTables> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSTABLES,
    payload: axios.get<IDsTables>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsTables> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSTABLES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsTables> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSTABLES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsTables> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSTABLES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
