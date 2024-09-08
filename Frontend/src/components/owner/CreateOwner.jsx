import React from 'react';
import OwnerForm from './OwnerForm';

function CreateOwner({ modal, toggle, save }) {
    return (
        <OwnerForm
            modal={modal}
            toggle={toggle}
            onSave={save}
        />
    );
}

export default CreateOwner;