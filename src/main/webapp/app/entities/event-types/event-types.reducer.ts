import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEventTypes, defaultValue } from 'app/shared/model/event-types.model';

export const ACTION_TYPES = {
  FETCH_EVENTTYPES_LIST: 'eventTypes/FETCH_EVENTTYPES_LIST',
  FETCH_EVENTTYPES: 'eventTypes/FETCH_EVENTTYPES',
  CREATE_EVENTTYPES: 'eventTypes/CREATE_EVENTTYPES',
  UPDATE_EVENTTYPES: 'eventTypes/UPDATE_EVENTTYPES',
  DELETE_EVENTTYPES: 'eventTypes/DELETE_EVENTTYPES',
  RESET: 'eventTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEventTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EventTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: EventTypesState = initialState, action): EventTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EVENTTYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EVENTTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EVENTTYPES):
    case REQUEST(ACTION_TYPES.UPDATE_EVENTTYPES):
    case REQUEST(ACTION_TYPES.DELETE_EVENTTYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EVENTTYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EVENTTYPES):
    case FAILURE(ACTION_TYPES.CREATE_EVENTTYPES):
    case FAILURE(ACTION_TYPES.UPDATE_EVENTTYPES):
    case FAILURE(ACTION_TYPES.DELETE_EVENTTYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTTYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTTYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EVENTTYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_EVENTTYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EVENTTYPES):
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

const apiUrl = 'api/event-types';

// Actions

export const getEntities: ICrudGetAllAction<IEventTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTTYPES_LIST,
    payload: axios.get<IEventTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEventTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTTYPES,
    payload: axios.get<IEventTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEventTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EVENTTYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEventTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EVENTTYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEventTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EVENTTYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
