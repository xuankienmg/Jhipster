import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardTypes, defaultValue } from 'app/shared/model/dq-standard-types.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDTYPES_LIST: 'dqStandardTypes/FETCH_DQSTANDARDTYPES_LIST',
  FETCH_DQSTANDARDTYPES: 'dqStandardTypes/FETCH_DQSTANDARDTYPES',
  CREATE_DQSTANDARDTYPES: 'dqStandardTypes/CREATE_DQSTANDARDTYPES',
  UPDATE_DQSTANDARDTYPES: 'dqStandardTypes/UPDATE_DQSTANDARDTYPES',
  DELETE_DQSTANDARDTYPES: 'dqStandardTypes/DELETE_DQSTANDARDTYPES',
  RESET: 'dqStandardTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardTypesState = initialState, action): DqStandardTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDTYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDTYPES):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDTYPES):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDTYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDTYPES):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDTYPES):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDTYPES):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDTYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDTYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDTYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDTYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDTYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDTYPES):
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

const apiUrl = 'api/dq-standard-types';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDTYPES_LIST,
    payload: axios.get<IDqStandardTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDTYPES,
    payload: axios.get<IDqStandardTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDTYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDTYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDTYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
