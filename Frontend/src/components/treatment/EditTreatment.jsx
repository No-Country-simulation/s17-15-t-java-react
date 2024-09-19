import React from 'react';
import TreatmentForm from './TreatmentForm.jsx';

function EditTreatment({ modal, toggle, updateTreatment, objTreatment }) {
    return (
        <TreatmentForm
            modal={modal}
            toggle={toggle}
            onSave={updateTreatment}
            objTreatment={objTreatment}
            // isEdit={true}
        />
    );
}

export default EditTreatment;