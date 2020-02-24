import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDataDefinition, defaultValue } from 'app/shared/model/data-definition.model';

export const ACTION_TYPES = {
  FETCH_DATADEFINITION_LIST: 'dataDefinition/FETCH_DATADEFINITION_LIST',
  FETCH_DATADEFINITION: 'dataDefinition/FETCH_DATADEFINITION',
  CREATE_DATADEFINITION: 'dataDefinition/CREATE_DATADEFINITION',
  UPDATE_DATADEFINITION: 'dataDefinition/UPDATE_DATADEFINITION',
  DELETE_DATADEFINITION: 'dataDefinition/DELETE_DATADEFINITION',
  RESET: 'dataDefinition/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDataDefinition>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DataDefinitionState = Readonly<typeof initialState>;

// Reducer

export default (state: DataDefinitionState = initialState, action): DataDefinitionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DATADEFINITION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DATADEFINITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DATADEFINITION):
    case REQUEST(ACTION_TYPES.UPDATE_DATADEFINITION):
    case REQUEST(ACTION_TYPES.DELETE_DATADEFINITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DATADEFINITION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DATADEFINITION):
    case FAILURE(ACTION_TYPES.CREATE_DATADEFINITION):
    case FAILURE(ACTION_TYPES.UPDATE_DATADEFINITION):
    case FAILURE(ACTION_TYPES.DELETE_DATADEFINITION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DATADEFINITION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DATADEFINITION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DATADEFINITION):
    case SUCCESS(ACTION_TYPES.UPDATE_DATADEFINITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DATADEFINITION):
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

const apiUrl = 'api/data-definitions';

// Actions

export const getEntities: ICrudGetAllAction<IDataDefinition> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DATADEFINITION_LIST,
    payload: axios.get<IDataDefinition>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDataDefinition> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DATADEFINITION,
    payload: axios.get<IDataDefinition>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDataDefinition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DATADEFINITION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDataDefinition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DATADEFINITION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDataDefinition> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DATADEFINITION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
