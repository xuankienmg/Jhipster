import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtlPackages, defaultValue } from 'app/shared/model/etl-packages.model';

export const ACTION_TYPES = {
  FETCH_ETLPACKAGES_LIST: 'etlPackages/FETCH_ETLPACKAGES_LIST',
  FETCH_ETLPACKAGES: 'etlPackages/FETCH_ETLPACKAGES',
  CREATE_ETLPACKAGES: 'etlPackages/CREATE_ETLPACKAGES',
  UPDATE_ETLPACKAGES: 'etlPackages/UPDATE_ETLPACKAGES',
  DELETE_ETLPACKAGES: 'etlPackages/DELETE_ETLPACKAGES',
  RESET: 'etlPackages/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtlPackages>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EtlPackagesState = Readonly<typeof initialState>;

// Reducer

export default (state: EtlPackagesState = initialState, action): EtlPackagesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETLPACKAGES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETLPACKAGES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETLPACKAGES):
    case REQUEST(ACTION_TYPES.UPDATE_ETLPACKAGES):
    case REQUEST(ACTION_TYPES.DELETE_ETLPACKAGES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETLPACKAGES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETLPACKAGES):
    case FAILURE(ACTION_TYPES.CREATE_ETLPACKAGES):
    case FAILURE(ACTION_TYPES.UPDATE_ETLPACKAGES):
    case FAILURE(ACTION_TYPES.DELETE_ETLPACKAGES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETLPACKAGES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETLPACKAGES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETLPACKAGES):
    case SUCCESS(ACTION_TYPES.UPDATE_ETLPACKAGES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETLPACKAGES):
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

const apiUrl = 'api/etl-packages';

// Actions

export const getEntities: ICrudGetAllAction<IEtlPackages> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ETLPACKAGES_LIST,
    payload: axios.get<IEtlPackages>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEtlPackages> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETLPACKAGES,
    payload: axios.get<IEtlPackages>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtlPackages> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETLPACKAGES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtlPackages> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETLPACKAGES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtlPackages> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETLPACKAGES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
