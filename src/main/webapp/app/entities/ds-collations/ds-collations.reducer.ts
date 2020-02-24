import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsCollations, defaultValue } from 'app/shared/model/ds-collations.model';

export const ACTION_TYPES = {
  FETCH_DSCOLLATIONS_LIST: 'dsCollations/FETCH_DSCOLLATIONS_LIST',
  FETCH_DSCOLLATIONS: 'dsCollations/FETCH_DSCOLLATIONS',
  CREATE_DSCOLLATIONS: 'dsCollations/CREATE_DSCOLLATIONS',
  UPDATE_DSCOLLATIONS: 'dsCollations/UPDATE_DSCOLLATIONS',
  DELETE_DSCOLLATIONS: 'dsCollations/DELETE_DSCOLLATIONS',
  RESET: 'dsCollations/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsCollations>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsCollationsState = Readonly<typeof initialState>;

// Reducer

export default (state: DsCollationsState = initialState, action): DsCollationsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSCOLLATIONS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSCOLLATIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSCOLLATIONS):
    case REQUEST(ACTION_TYPES.UPDATE_DSCOLLATIONS):
    case REQUEST(ACTION_TYPES.DELETE_DSCOLLATIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSCOLLATIONS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSCOLLATIONS):
    case FAILURE(ACTION_TYPES.CREATE_DSCOLLATIONS):
    case FAILURE(ACTION_TYPES.UPDATE_DSCOLLATIONS):
    case FAILURE(ACTION_TYPES.DELETE_DSCOLLATIONS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLLATIONS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSCOLLATIONS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSCOLLATIONS):
    case SUCCESS(ACTION_TYPES.UPDATE_DSCOLLATIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSCOLLATIONS):
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

const apiUrl = 'api/ds-collations';

// Actions

export const getEntities: ICrudGetAllAction<IDsCollations> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLLATIONS_LIST,
    payload: axios.get<IDsCollations>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsCollations> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSCOLLATIONS,
    payload: axios.get<IDsCollations>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsCollations> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSCOLLATIONS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsCollations> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSCOLLATIONS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsCollations> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSCOLLATIONS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
