import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsStores, defaultValue } from 'app/shared/model/ds-stores.model';

export const ACTION_TYPES = {
  FETCH_DSSTORES_LIST: 'dsStores/FETCH_DSSTORES_LIST',
  FETCH_DSSTORES: 'dsStores/FETCH_DSSTORES',
  CREATE_DSSTORES: 'dsStores/CREATE_DSSTORES',
  UPDATE_DSSTORES: 'dsStores/UPDATE_DSSTORES',
  DELETE_DSSTORES: 'dsStores/DELETE_DSSTORES',
  RESET: 'dsStores/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsStores>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsStoresState = Readonly<typeof initialState>;

// Reducer

export default (state: DsStoresState = initialState, action): DsStoresState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSSTORES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSSTORES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSSTORES):
    case REQUEST(ACTION_TYPES.UPDATE_DSSTORES):
    case REQUEST(ACTION_TYPES.DELETE_DSSTORES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSSTORES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSSTORES):
    case FAILURE(ACTION_TYPES.CREATE_DSSTORES):
    case FAILURE(ACTION_TYPES.UPDATE_DSSTORES):
    case FAILURE(ACTION_TYPES.DELETE_DSSTORES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSSTORES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSSTORES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSSTORES):
    case SUCCESS(ACTION_TYPES.UPDATE_DSSTORES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSSTORES):
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

const apiUrl = 'api/ds-stores';

// Actions

export const getEntities: ICrudGetAllAction<IDsStores> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSSTORES_LIST,
    payload: axios.get<IDsStores>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsStores> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSSTORES,
    payload: axios.get<IDsStores>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsStores> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSSTORES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsStores> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSSTORES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsStores> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSSTORES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
