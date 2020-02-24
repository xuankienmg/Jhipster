import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsColumnTypes, defaultValue } from 'app/shared/model/ds-column-types.model';

export const ACTION_TYPES = {
  FETCH_DSCOLUMNTYPES_LIST: 'dsColumnTypes/FETCH_DSCOLUMNTYPES_LIST',
  FETCH_DSCOLUMNTYPES: 'dsColumnTypes/FETCH_DSCOLUMNTYPES',
  CREATE_DSCOLUMNTYPES: 'dsColumnTypes/CREATE_DSCOLUMNTYPES',
  UPDATE_DSCOLUMNTYPES: 'dsColumnTypes/UPDATE_DSCOLUMNTYPES',
  DELETE_DSCOLUMNTYPES: 'dsColumnTypes/DELETE_DSCOLUMNTYPES',
  RESET: 'dsColumnTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsColumnTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsColumnTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: DsColumnTypesState = initialState, action): DsColumnTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSCOLUMNTYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSCOLUMNTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSCOLUMNTYPES):
    case REQUEST(ACTION_TYPES.UPDATE_DSCOLUMNTYPES):
    case REQUEST(ACTION_TYPES.DELETE_DSCOLUMNTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSCOLUMNTYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSCOLUMNTYPES):
    case FAILURE(ACTION_TYPES.CREATE_DSCOLUMNTYPES):
    case FAILURE(ACTION_TYPES.UPDATE_DSCOLUMNTYPES):
    case FAILURE(ACTION_TYPES.DELETE_DSCOLUMNTYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLUMNTYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLUMNTYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSCOLUMNTYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_DSCOLUMNTYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSCOLUMNTYPES):
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

const apiUrl = 'api/ds-column-types';

// Actions

export const getEntities: ICrudGetAllAction<IDsColumnTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLUMNTYPES_LIST,
    payload: axios.get<IDsColumnTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsColumnTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLUMNTYPES,
    payload: axios.get<IDsColumnTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsColumnTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSCOLUMNTYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsColumnTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSCOLUMNTYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsColumnTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSCOLUMNTYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
