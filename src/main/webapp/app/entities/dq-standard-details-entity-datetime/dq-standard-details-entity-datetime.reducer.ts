import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityDatetime, defaultValue } from 'app/shared/model/dq-standard-details-entity-datetime.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST: 'dqStandardDetailsEntityDatetime/FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST',
  FETCH_DQSTANDARDDETAILSENTITYDATETIME: 'dqStandardDetailsEntityDatetime/FETCH_DQSTANDARDDETAILSENTITYDATETIME',
  CREATE_DQSTANDARDDETAILSENTITYDATETIME: 'dqStandardDetailsEntityDatetime/CREATE_DQSTANDARDDETAILSENTITYDATETIME',
  UPDATE_DQSTANDARDDETAILSENTITYDATETIME: 'dqStandardDetailsEntityDatetime/UPDATE_DQSTANDARDDETAILSENTITYDATETIME',
  DELETE_DQSTANDARDDETAILSENTITYDATETIME: 'dqStandardDetailsEntityDatetime/DELETE_DQSTANDARDDETAILSENTITYDATETIME',
  RESET: 'dqStandardDetailsEntityDatetime/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityDatetime>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityDatetimeState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityDatetimeState = initialState, action): DqStandardDetailsEntityDatetimeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDATETIME):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDATETIME):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDATETIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDATETIME):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDATETIME):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDATETIME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDATETIME):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDATETIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDATETIME):
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

const apiUrl = 'api/dq-standard-details-entity-datetimes';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityDatetime> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME_LIST,
    payload: axios.get<IDqStandardDetailsEntityDatetime>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityDatetime> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDATETIME,
    payload: axios.get<IDqStandardDetailsEntityDatetime>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityDatetime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDATETIME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityDatetime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDATETIME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityDatetime> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDATETIME,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
