import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityInt, defaultValue } from 'app/shared/model/dq-standard-details-entity-int.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYINT_LIST: 'dqStandardDetailsEntityInt/FETCH_DQSTANDARDDETAILSENTITYINT_LIST',
  FETCH_DQSTANDARDDETAILSENTITYINT: 'dqStandardDetailsEntityInt/FETCH_DQSTANDARDDETAILSENTITYINT',
  CREATE_DQSTANDARDDETAILSENTITYINT: 'dqStandardDetailsEntityInt/CREATE_DQSTANDARDDETAILSENTITYINT',
  UPDATE_DQSTANDARDDETAILSENTITYINT: 'dqStandardDetailsEntityInt/UPDATE_DQSTANDARDDETAILSENTITYINT',
  DELETE_DQSTANDARDDETAILSENTITYINT: 'dqStandardDetailsEntityInt/DELETE_DQSTANDARDDETAILSENTITYINT',
  RESET: 'dqStandardDetailsEntityInt/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityInt>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityIntState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityIntState = initialState, action): DqStandardDetailsEntityIntState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYINT):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYINT):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYINT):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYINT):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYINT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYINT):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYINT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYINT):
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

const apiUrl = 'api/dq-standard-details-entity-ints';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityInt> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT_LIST,
    payload: axios.get<IDqStandardDetailsEntityInt>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityInt> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYINT,
    payload: axios.get<IDqStandardDetailsEntityInt>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityInt> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYINT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityInt> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYINT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityInt> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYINT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
