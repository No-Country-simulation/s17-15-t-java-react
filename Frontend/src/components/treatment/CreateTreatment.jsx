import React from 'react';

import TreatmentForm from './TreatmentForm.jsx';

function CreateTreatment({ modal, toggle, save, idPet }) {
    return (
        <TreatmentForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idPet={idPet}
        />
    );
}

export default CreateTreatment;