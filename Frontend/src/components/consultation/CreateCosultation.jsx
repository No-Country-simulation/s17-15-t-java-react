import React from 'react';
import ConsultationForm from './ConsultationForm';


function CreateConsultation({ modal, toggle, save, idOwner }) {
    return (
        <ConsultationForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idOwner={idOwner}
        />
    );
}

export default CreateConsultation;