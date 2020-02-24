import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDqStandardDetailsEntityDatetime } from 'app/shared/model/dq-standard-details-entity-datetime.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './dq-standard-details-entity-datetime.reducer';

export interface IDqStandardDetailsEntityDatetimeDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityDatetimeDeleteDialog = (props: IDqStandardDetailsEntityDatetimeDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/dq-standard-details-entity-datetime' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.dqStandardDetailsEntityDatetimeEntity.id);
  };

  const { dqStandardDetailsEntityDatetimeEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jhipsterApp.dqStandardDetailsEntityDatetime.delete.question">
        <Translate
          contentKey="jhipsterApp.dqStandardDetailsEntityDatetime.delete.question"
          interpolate={{ id: dqStandardDetailsEntityDatetimeEntity.id }}
        >
          Are you sure you want to delete this DqStandardDetailsEntityDatetime?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-dqStandardDetailsEntityDatetime" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityDatetime }: IRootState) => ({
  dqStandardDetailsEntityDatetimeEntity: dqStandardDetailsEntityDatetime.entity,
  updateSuccess: dqStandardDetailsEntityDatetime.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityDatetimeDeleteDialog);
