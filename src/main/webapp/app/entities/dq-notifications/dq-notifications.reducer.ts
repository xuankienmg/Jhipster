import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqNotifications, defaultValue } from 'app/shared/model/dq-notifications.model';

export const ACTION_TYPES = {
  FETCH_DQNOTIFICATIONS_LIST: 'dqNotifications/FETCH_DQNOTIFICATIONS_LIST',
  FETCH_DQNOTIFICATIONS: 'dqNotifications/FETCH_DQNOTIFICATIONS',
  CREATE_DQNOTIFICATIONS: 'dqNotifications/CREATE_DQNOTIFICATIONS',
  UPDATE_DQNOTIFICATIONS: 'dqNotifications/UPDATE_DQNOTIFICATIONS',
  DELETE_DQNOTIFICATIONS: 'dqNotifications/DELETE_DQNOTIFICATIONS',
  RESET: 'dqNotifications/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqNotifications>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqNotificationsState = Readonly<typeof initialState>;

// Reducer

export default (state: DqNotificationsState = initialState, action): DqNotificationsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQNOTIFICATIONS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQNOTIFICATIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQNOTIFICATIONS):
    case REQUEST(ACTION_TYPES.UPDATE_DQNOTIFICATIONS):
    case REQUEST(ACTION_TYPES.DELETE_DQNOTIFICATIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQNOTIFICATIONS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQNOTIFICATIONS):
    case FAILURE(ACTION_TYPES.CREATE_DQNOTIFICATIONS):
    case FAILURE(ACTION_TYPES.UPDATE_DQNOTIFICATIONS):
    case FAILURE(ACTION_TYPES.DELETE_DQNOTIFICATIONS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQNOTIFICATIONS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQNOTIFICATIONS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQNOTIFICATIONS):
    case SUCCESS(ACTION_TYPES.UPDATE_DQNOTIFICATIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQNOTIFICATIONS):
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

const apiUrl = 'api/dq-notifications';

// Actions

export const getEntities: ICrudGetAllAction<IDqNotifications> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQNOTIFICATIONS_LIST,
    payload: axios.get<IDqNotifications>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqNotifications> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQNOTIFICATIONS,
    payload: axios.get<IDqNotifications>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqNotifications> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQNOTIFICATIONS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqNotifications> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQNOTIFICATIONS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqNotifications> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQNOTIFICATIONS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
