import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandards, defaultValue } from 'app/shared/model/dq-standards.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDS_LIST: 'dqStandards/FETCH_DQSTANDARDS_LIST',
  FETCH_DQSTANDARDS: 'dqStandards/FETCH_DQSTANDARDS',
  CREATE_DQSTANDARDS: 'dqStandards/CREATE_DQSTANDARDS',
  UPDATE_DQSTANDARDS: 'dqStandards/UPDATE_DQSTANDARDS',
  DELETE_DQSTANDARDS: 'dqStandards/DELETE_DQSTANDARDS',
  RESET: 'dqStandards/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandards>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardsState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardsState = initialState, action): DqStandardsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDS):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDS):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDS):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDS):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDS):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDS):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDS):
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

const apiUrl = 'api/dq-standards';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandards> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDS_LIST,
    payload: axios.get<IDqStandards>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandards> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDS,
    payload: axios.get<IDqStandards>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandards> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandards> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandards> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
