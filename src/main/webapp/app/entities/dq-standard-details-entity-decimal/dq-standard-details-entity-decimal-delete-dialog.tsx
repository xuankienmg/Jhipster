import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDqStandardDetailsEntityDecimal } from 'app/shared/model/dq-standard-details-entity-decimal.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './dq-standard-details-entity-decimal.reducer';

export interface IDqStandardDetailsEntityDecimalDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqStandardDetailsEntityDecimalDeleteDialog = (props: IDqStandardDetailsEntityDecimalDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/dq-standard-details-entity-decimal' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.dqStandardDetailsEntityDecimalEntity.id);
  };

  const { dqStandardDetailsEntityDecimalEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jhipsterApp.dqStandardDetailsEntityDecimal.delete.question">
        <Translate
          contentKey="jhipsterApp.dqStandardDetailsEntityDecimal.delete.question"
          interpolate={{ id: dqStandardDetailsEntityDecimalEntity.id }}
        >
          Are you sure you want to delete this DqStandardDetailsEntityDecimal?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-dqStandardDetailsEntityDecimal" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ dqStandardDetailsEntityDecimal }: IRootState) => ({
  dqStandardDetailsEntityDecimalEntity: dqStandardDetailsEntityDecimal.entity,
  updateSuccess: dqStandardDetailsEntityDecimal.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityDecimalDeleteDialog);
