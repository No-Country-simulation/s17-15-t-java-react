import React from 'react';
import OwnerForm from './OwnerForm';

function EditOwner({ modal, toggle, updateOwner, objOwner }) {
    return (
        <OwnerForm
            modal={modal}
            toggle={toggle}
            onSave={updateOwner}
            objOwner={objOwner}
            isEdit={true}
        />
    );
}

export default EditOwner;