import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityVarchar, defaultValue } from 'app/shared/model/dq-standard-details-entity-varchar.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST: 'dqStandardDetailsEntityVarchar/FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST',
  FETCH_DQSTANDARDDETAILSENTITYVARCHAR: 'dqStandardDetailsEntityVarchar/FETCH_DQSTANDARDDETAILSENTITYVARCHAR',
  CREATE_DQSTANDARDDETAILSENTITYVARCHAR: 'dqStandardDetailsEntityVarchar/CREATE_DQSTANDARDDETAILSENTITYVARCHAR',
  UPDATE_DQSTANDARDDETAILSENTITYVARCHAR: 'dqStandardDetailsEntityVarchar/UPDATE_DQSTANDARDDETAILSENTITYVARCHAR',
  DELETE_DQSTANDARDDETAILSENTITYVARCHAR: 'dqStandardDetailsEntityVarchar/DELETE_DQSTANDARDDETAILSENTITYVARCHAR',
  RESET: 'dqStandardDetailsEntityVarchar/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityVarchar>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityVarcharState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityVarcharState = initialState, action): DqStandardDetailsEntityVarcharState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYVARCHAR):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYVARCHAR):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYVARCHAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYVARCHAR):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYVARCHAR):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYVARCHAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYVARCHAR):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYVARCHAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYVARCHAR):
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

const apiUrl = 'api/dq-standard-details-entity-varchars';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityVarchar> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR_LIST,
    payload: axios.get<IDqStandardDetailsEntityVarchar>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityVarchar> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYVARCHAR,
    payload: axios.get<IDqStandardDetailsEntityVarchar>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityVarchar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYVARCHAR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityVarchar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYVARCHAR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityVarchar> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYVARCHAR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
