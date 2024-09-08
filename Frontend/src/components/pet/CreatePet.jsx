import React from 'react';
import PetForm from './PetForm';


function CreatePet({ modal, toggle, save, idsong }) {
    return (
        <PetForm
            modal={modal}
            toggle={toggle}
            onSave={save}
            idsong={idsong}
        />
    );
}

export default CreatePet;