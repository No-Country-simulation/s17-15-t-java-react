import React from 'react';
import ConsultationForm from './ConsultationForm';


function CreateConsultation({ modal, toggle, save, idPet }) {
    return (
        <ConsultationForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idPet={idPet}
        />
    );
}

export default CreateConsultation;