import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityDecimal, defaultValue } from 'app/shared/model/dq-standard-details-entity-decimal.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST: 'dqStandardDetailsEntityDecimal/FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST',
  FETCH_DQSTANDARDDETAILSENTITYDECIMAL: 'dqStandardDetailsEntityDecimal/FETCH_DQSTANDARDDETAILSENTITYDECIMAL',
  CREATE_DQSTANDARDDETAILSENTITYDECIMAL: 'dqStandardDetailsEntityDecimal/CREATE_DQSTANDARDDETAILSENTITYDECIMAL',
  UPDATE_DQSTANDARDDETAILSENTITYDECIMAL: 'dqStandardDetailsEntityDecimal/UPDATE_DQSTANDARDDETAILSENTITYDECIMAL',
  DELETE_DQSTANDARDDETAILSENTITYDECIMAL: 'dqStandardDetailsEntityDecimal/DELETE_DQSTANDARDDETAILSENTITYDECIMAL',
  RESET: 'dqStandardDetailsEntityDecimal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityDecimal>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityDecimalState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityDecimalState = initialState, action): DqStandardDetailsEntityDecimalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDECIMAL):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDECIMAL):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDECIMAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDECIMAL):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDECIMAL):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDECIMAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDECIMAL):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDECIMAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDECIMAL):
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

const apiUrl = 'api/dq-standard-details-entity-decimals';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityDecimal> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL_LIST,
    payload: axios.get<IDqStandardDetailsEntityDecimal>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityDecimal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYDECIMAL,
    payload: axios.get<IDqStandardDetailsEntityDecimal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityDecimal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYDECIMAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityDecimal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYDECIMAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityDecimal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYDECIMAL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
