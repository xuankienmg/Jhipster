import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEventLogs, defaultValue } from 'app/shared/model/event-logs.model';

export const ACTION_TYPES = {
  FETCH_EVENTLOGS_LIST: 'eventLogs/FETCH_EVENTLOGS_LIST',
  FETCH_EVENTLOGS: 'eventLogs/FETCH_EVENTLOGS',
  CREATE_EVENTLOGS: 'eventLogs/CREATE_EVENTLOGS',
  UPDATE_EVENTLOGS: 'eventLogs/UPDATE_EVENTLOGS',
  DELETE_EVENTLOGS: 'eventLogs/DELETE_EVENTLOGS',
  RESET: 'eventLogs/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEventLogs>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EventLogsState = Readonly<typeof initialState>;

// Reducer

export default (state: EventLogsState = initialState, action): EventLogsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EVENTLOGS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EVENTLOGS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EVENTLOGS):
    case REQUEST(ACTION_TYPES.UPDATE_EVENTLOGS):
    case REQUEST(ACTION_TYPES.DELETE_EVENTLOGS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EVENTLOGS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EVENTLOGS):
    case FAILURE(ACTION_TYPES.CREATE_EVENTLOGS):
    case FAILURE(ACTION_TYPES.UPDATE_EVENTLOGS):
    case FAILURE(ACTION_TYPES.DELETE_EVENTLOGS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTLOGS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTLOGS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EVENTLOGS):
    case SUCCESS(ACTION_TYPES.UPDATE_EVENTLOGS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EVENTLOGS):
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

const apiUrl = 'api/event-logs';

// Actions

export const getEntities: ICrudGetAllAction<IEventLogs> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTLOGS_LIST,
    payload: axios.get<IEventLogs>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEventLogs> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTLOGS,
    payload: axios.get<IEventLogs>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEventLogs> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EVENTLOGS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEventLogs> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EVENTLOGS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEventLogs> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EVENTLOGS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
