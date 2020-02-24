import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDqStandardDetailsEntityTime } from 'app/shared/model/dq-standard-details-entity-time.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './dq-standard-details-entity-time.reducer';

export interface IDqStandardDetailsEntityTimeDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityTimeDeleteDialog = (props: IDqStandardDetailsEntityTimeDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/dq-standard-details-entity-time' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.dqStandardDetailsEntityTimeEntity.id);
  };

  const { dqStandardDetailsEntityTimeEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jhipsterApp.dqStandardDetailsEntityTime.delete.question">
        <Translate
          contentKey="jhipsterApp.dqStandardDetailsEntityTime.delete.question"
          interpolate={{ id: dqStandardDetailsEntityTimeEntity.id }}
        >
          Are you sure you want to delete this DqStandardDetailsEntityTime?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-dqStandardDetailsEntityTime" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityTime }: IRootState) => ({
  dqStandardDetailsEntityTimeEntity: dqStandardDetailsEntityTime.entity,
  updateSuccess: dqStandardDetailsEntityTime.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityTimeDeleteDialog);
