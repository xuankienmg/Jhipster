import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityTime, defaultValue } from 'app/shared/model/dq-standard-details-entity-time.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYTIME_LIST: 'dqStandardDetailsEntityTime/FETCH_DQSTANDARDDETAILSENTITYTIME_LIST',
  FETCH_DQSTANDARDDETAILSENTITYTIME: 'dqStandardDetailsEntityTime/FETCH_DQSTANDARDDETAILSENTITYTIME',
  CREATE_DQSTANDARDDETAILSENTITYTIME: 'dqStandardDetailsEntityTime/CREATE_DQSTANDARDDETAILSENTITYTIME',
  UPDATE_DQSTANDARDDETAILSENTITYTIME: 'dqStandardDetailsEntityTime/UPDATE_DQSTANDARDDETAILSENTITYTIME',
  DELETE_DQSTANDARDDETAILSENTITYTIME: 'dqStandardDetailsEntityTime/DELETE_DQSTANDARDDETAILSENTITYTIME',
  RESET: 'dqStandardDetailsEntityTime/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityTime>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityTimeState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityTimeState = initialState, action): DqStandardDetailsEntityTimeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTIME):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTIME):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTIME):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTIME):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTIME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTIME):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTIME):
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

const apiUrl = 'api/dq-standard-details-entity-times';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityTime> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME_LIST,
    payload: axios.get<IDqStandardDetailsEntityTime>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityTime> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTIME,
    payload: axios.get<IDqStandardDetailsEntityTime>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityTime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTIME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityTime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTIME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityTime> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTIME,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
