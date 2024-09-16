import React from 'react';

import TreatmentForm from './TreatmentForm.jsx';

function CreateTreatment({ modal, toggle, save,  idDiagnosis, idHospitalization }) {
    return (
        <TreatmentForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idDiagnosis={idDiagnosis}
            idHospitalization={idHospitalization}
        />
    );
}

export default CreateTreatment;