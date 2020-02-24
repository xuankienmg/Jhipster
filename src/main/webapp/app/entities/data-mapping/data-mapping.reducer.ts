import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDataMapping, defaultValue } from 'app/shared/model/data-mapping.model';

export const ACTION_TYPES = {
  FETCH_DATAMAPPING_LIST: 'dataMapping/FETCH_DATAMAPPING_LIST',
  FETCH_DATAMAPPING: 'dataMapping/FETCH_DATAMAPPING',
  CREATE_DATAMAPPING: 'dataMapping/CREATE_DATAMAPPING',
  UPDATE_DATAMAPPING: 'dataMapping/UPDATE_DATAMAPPING',
  DELETE_DATAMAPPING: 'dataMapping/DELETE_DATAMAPPING',
  RESET: 'dataMapping/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDataMapping>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DataMappingState = Readonly<typeof initialState>;

// Reducer

export default (state: DataMappingState = initialState, action): DataMappingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DATAMAPPING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DATAMAPPING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DATAMAPPING):
    case REQUEST(ACTION_TYPES.UPDATE_DATAMAPPING):
    case REQUEST(ACTION_TYPES.DELETE_DATAMAPPING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DATAMAPPING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DATAMAPPING):
    case FAILURE(ACTION_TYPES.CREATE_DATAMAPPING):
    case FAILURE(ACTION_TYPES.UPDATE_DATAMAPPING):
    case FAILURE(ACTION_TYPES.DELETE_DATAMAPPING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DATAMAPPING_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DATAMAPPING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DATAMAPPING):
    case SUCCESS(ACTION_TYPES.UPDATE_DATAMAPPING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DATAMAPPING):
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

const apiUrl = 'api/data-mappings';

// Actions

export const getEntities: ICrudGetAllAction<IDataMapping> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DATAMAPPING_LIST,
    payload: axios.get<IDataMapping>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDataMapping> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DATAMAPPING,
    payload: axios.get<IDataMapping>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDataMapping> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DATAMAPPING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDataMapping> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DATAMAPPING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDataMapping> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DATAMAPPING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
