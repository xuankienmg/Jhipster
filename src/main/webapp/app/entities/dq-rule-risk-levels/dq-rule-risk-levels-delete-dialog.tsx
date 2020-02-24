import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDqRuleRiskLevels } from 'app/shared/model/dq-rule-risk-levels.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './dq-rule-risk-levels.reducer';

export interface IDqRuleRiskLevelsDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleRiskLevelsDeleteDialog = (props: IDqRuleRiskLevelsDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/dq-rule-risk-levels' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.dqRuleRiskLevelsEntity.id);
  };

  const { dqRuleRiskLevelsEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jhipsterApp.dqRuleRiskLevels.delete.question">
        <Translate contentKey="jhipsterApp.dqRuleRiskLevels.delete.question" interpolate={{ id: dqRuleRiskLevelsEntity.id }}>
          Are you sure you want to delete this DqRuleRiskLevels?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-dqRuleRiskLevels" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ dqRuleRiskLevels }: IRootState) => ({
  dqRuleRiskLevelsEntity: dqRuleRiskLevels.entity,
  updateSuccess: dqRuleRiskLevels.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleRiskLevelsDeleteDialog);
