import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsColumns, defaultValue } from 'app/shared/model/ds-columns.model';

export const ACTION_TYPES = {
  FETCH_DSCOLUMNS_LIST: 'dsColumns/FETCH_DSCOLUMNS_LIST',
  FETCH_DSCOLUMNS: 'dsColumns/FETCH_DSCOLUMNS',
  CREATE_DSCOLUMNS: 'dsColumns/CREATE_DSCOLUMNS',
  UPDATE_DSCOLUMNS: 'dsColumns/UPDATE_DSCOLUMNS',
  DELETE_DSCOLUMNS: 'dsColumns/DELETE_DSCOLUMNS',
  RESET: 'dsColumns/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsColumns>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsColumnsState = Readonly<typeof initialState>;

// Reducer

export default (state: DsColumnsState = initialState, action): DsColumnsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSCOLUMNS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSCOLUMNS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSCOLUMNS):
    case REQUEST(ACTION_TYPES.UPDATE_DSCOLUMNS):
    case REQUEST(ACTION_TYPES.DELETE_DSCOLUMNS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSCOLUMNS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSCOLUMNS):
    case FAILURE(ACTION_TYPES.CREATE_DSCOLUMNS):
    case FAILURE(ACTION_TYPES.UPDATE_DSCOLUMNS):
    case FAILURE(ACTION_TYPES.DELETE_DSCOLUMNS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLUMNS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLUMNS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSCOLUMNS):
    case SUCCESS(ACTION_TYPES.UPDATE_DSCOLUMNS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSCOLUMNS):
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

const apiUrl = 'api/ds-columns';

// Actions

export const getEntities: ICrudGetAllAction<IDsColumns> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLUMNS_LIST,
    payload: axios.get<IDsColumns>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsColumns> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLUMNS,
    payload: axios.get<IDsColumns>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsColumns> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSCOLUMNS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsColumns> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSCOLUMNS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsColumns> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSCOLUMNS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
