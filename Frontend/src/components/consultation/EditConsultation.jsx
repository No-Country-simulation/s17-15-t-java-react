import React from 'react';
import ConsultationForm from './ConsultationForm';

function EditConsultation({ modal, toggle, updateItem, objItem }) {
    return (
        <ConsultationForm
            modal={modal}
            toggle={toggle}
            onSave={updateItem}
            objItem={objItem}
            isEdit={true}
        />
    );
}

export default EditConsultation;