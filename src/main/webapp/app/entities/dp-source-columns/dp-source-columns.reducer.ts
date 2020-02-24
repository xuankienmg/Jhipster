import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDpSourceColumns, defaultValue } from 'app/shared/model/dp-source-columns.model';

export const ACTION_TYPES = {
  FETCH_DPSOURCECOLUMNS_LIST: 'dpSourceColumns/FETCH_DPSOURCECOLUMNS_LIST',
  FETCH_DPSOURCECOLUMNS: 'dpSourceColumns/FETCH_DPSOURCECOLUMNS',
  CREATE_DPSOURCECOLUMNS: 'dpSourceColumns/CREATE_DPSOURCECOLUMNS',
  UPDATE_DPSOURCECOLUMNS: 'dpSourceColumns/UPDATE_DPSOURCECOLUMNS',
  DELETE_DPSOURCECOLUMNS: 'dpSourceColumns/DELETE_DPSOURCECOLUMNS',
  RESET: 'dpSourceColumns/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDpSourceColumns>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DpSourceColumnsState = Readonly<typeof initialState>;

// Reducer

export default (state: DpSourceColumnsState = initialState, action): DpSourceColumnsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DPSOURCECOLUMNS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DPSOURCECOLUMNS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DPSOURCECOLUMNS):
    case REQUEST(ACTION_TYPES.UPDATE_DPSOURCECOLUMNS):
    case REQUEST(ACTION_TYPES.DELETE_DPSOURCECOLUMNS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DPSOURCECOLUMNS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DPSOURCECOLUMNS):
    case FAILURE(ACTION_TYPES.CREATE_DPSOURCECOLUMNS):
    case FAILURE(ACTION_TYPES.UPDATE_DPSOURCECOLUMNS):
    case FAILURE(ACTION_TYPES.DELETE_DPSOURCECOLUMNS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPSOURCECOLUMNS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPSOURCECOLUMNS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DPSOURCECOLUMNS):
    case SUCCESS(ACTION_TYPES.UPDATE_DPSOURCECOLUMNS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DPSOURCECOLUMNS):
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

const apiUrl = 'api/dp-source-columns';

// Actions

export const getEntities: ICrudGetAllAction<IDpSourceColumns> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DPSOURCECOLUMNS_LIST,
    payload: axios.get<IDpSourceColumns>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDpSourceColumns> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DPSOURCECOLUMNS,
    payload: axios.get<IDpSourceColumns>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDpSourceColumns> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DPSOURCECOLUMNS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDpSourceColumns> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DPSOURCECOLUMNS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDpSourceColumns> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DPSOURCECOLUMNS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
