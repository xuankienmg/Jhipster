import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDqStandardDetailsEntityInt } from 'app/shared/model/dq-standard-details-entity-int.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './dq-standard-details-entity-int.reducer';

export interface IDqStandardDetailsEntityIntDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityIntDeleteDialog = (props: IDqStandardDetailsEntityIntDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/dq-standard-details-entity-int' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.dqStandardDetailsEntityIntEntity.id);
  };

  const { dqStandardDetailsEntityIntEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jhipsterApp.dqStandardDetailsEntityInt.delete.question">
        <Translate
          contentKey="jhipsterApp.dqStandardDetailsEntityInt.delete.question"
          interpolate={{ id: dqStandardDetailsEntityIntEntity.id }}
        >
          Are you sure you want to delete this DqStandardDetailsEntityInt?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-dqStandardDetailsEntityInt" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityInt }: IRootState) => ({
  dqStandardDetailsEntityIntEntity: dqStandardDetailsEntityInt.entity,
  updateSuccess: dqStandardDetailsEntityInt.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityIntDeleteDialog);
