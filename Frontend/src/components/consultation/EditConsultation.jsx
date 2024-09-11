import React from 'react';
import ConsultationForm from './ConsultationForm';

function EditConsultation({ modal, toggle, updateConsultation, objConsultation }) {
    return (
        <ConsultationForm
            modal={modal}
            toggle={toggle}
            onSave={updateConsultation}
            objConsultation={objConsultation}
            isEdit={true}
        />
    );
}

export default EditConsultation;