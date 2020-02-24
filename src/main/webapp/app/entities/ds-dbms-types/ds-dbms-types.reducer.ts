import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDsDbmsTypes, defaultValue } from 'app/shared/model/ds-dbms-types.model';

export const ACTION_TYPES = {
  FETCH_DSDBMSTYPES_LIST: 'dsDbmsTypes/FETCH_DSDBMSTYPES_LIST',
  FETCH_DSDBMSTYPES: 'dsDbmsTypes/FETCH_DSDBMSTYPES',
  CREATE_DSDBMSTYPES: 'dsDbmsTypes/CREATE_DSDBMSTYPES',
  UPDATE_DSDBMSTYPES: 'dsDbmsTypes/UPDATE_DSDBMSTYPES',
  DELETE_DSDBMSTYPES: 'dsDbmsTypes/DELETE_DSDBMSTYPES',
  RESET: 'dsDbmsTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDsDbmsTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DsDbmsTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: DsDbmsTypesState = initialState, action): DsDbmsTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DSDBMSTYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DSDBMSTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DSDBMSTYPES):
    case REQUEST(ACTION_TYPES.UPDATE_DSDBMSTYPES):
    case REQUEST(ACTION_TYPES.DELETE_DSDBMSTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DSDBMSTYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DSDBMSTYPES):
    case FAILURE(ACTION_TYPES.CREATE_DSDBMSTYPES):
    case FAILURE(ACTION_TYPES.UPDATE_DSDBMSTYPES):
    case FAILURE(ACTION_TYPES.DELETE_DSDBMSTYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSDBMSTYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DSDBMSTYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DSDBMSTYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_DSDBMSTYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DSDBMSTYPES):
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

const apiUrl = 'api/ds-dbms-types';

// Actions

export const getEntities: ICrudGetAllAction<IDsDbmsTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DSDBMSTYPES_LIST,
    payload: axios.get<IDsDbmsTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDsDbmsTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DSDBMSTYPES,
    payload: axios.get<IDsDbmsTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDsDbmsTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DSDBMSTYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDsDbmsTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DSDBMSTYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDsDbmsTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DSDBMSTYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
