import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsTableTypes, defaultValue } from 'app/shared/model/ds-table-types.model';

export const ACTION_TYPES = {
  FETCH_DSTABLETYPES_LIST: 'dsTableTypes/FETCH_DSTABLETYPES_LIST',
  FETCH_DSTABLETYPES: 'dsTableTypes/FETCH_DSTABLETYPES',
  CREATE_DSTABLETYPES: 'dsTableTypes/CREATE_DSTABLETYPES',
  UPDATE_DSTABLETYPES: 'dsTableTypes/UPDATE_DSTABLETYPES',
  DELETE_DSTABLETYPES: 'dsTableTypes/DELETE_DSTABLETYPES',
  RESET: 'dsTableTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsTableTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsTableTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: DsTableTypesState = initialState, action): DsTableTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSTABLETYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSTABLETYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSTABLETYPES):
    case REQUEST(ACTION_TYPES.UPDATE_DSTABLETYPES):
    case REQUEST(ACTION_TYPES.DELETE_DSTABLETYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSTABLETYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSTABLETYPES):
    case FAILURE(ACTION_TYPES.CREATE_DSTABLETYPES):
    case FAILURE(ACTION_TYPES.UPDATE_DSTABLETYPES):
    case FAILURE(ACTION_TYPES.DELETE_DSTABLETYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSTABLETYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSTABLETYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSTABLETYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_DSTABLETYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSTABLETYPES):
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

const apiUrl = 'api/ds-table-types';

// Actions

export const getEntities: ICrudGetAllAction<IDsTableTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSTABLETYPES_LIST,
    payload: axios.get<IDsTableTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsTableTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSTABLETYPES,
    payload: axios.get<IDsTableTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsTableTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSTABLETYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsTableTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSTABLETYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsTableTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSTABLETYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
