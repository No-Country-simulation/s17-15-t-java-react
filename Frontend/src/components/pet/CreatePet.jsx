import React from 'react';
import PetForm from './PetForm';


function CreatePet({ modal, toggle, save, idOwner }) {
    return (
        <PetForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idOwner={idOwner}
        />
    );
}

export default CreatePet;