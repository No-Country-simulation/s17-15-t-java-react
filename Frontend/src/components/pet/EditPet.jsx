import React from 'react';
import PetForm from './PetForm';

function EditPet({ modal, toggle, updatePet, objPet }) {
    return (
        <PetForm
            modal={modal}
            toggle={toggle}
            onSave={updatePet}
            objPet={objPet}
            isEdit={true}
        />
    );
}

export default EditPet;