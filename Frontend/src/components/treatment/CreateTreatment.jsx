import React from 'react';

import TreatmentForm from './TreatmentForm.jsx';

function CreateTreatment({ modal, toggle, save }) {
    return (
        <TreatmentForm
            modal={modal}
            toggle={toggle}
            onSave={save}
        />
    );
}

export default CreateTreatment;