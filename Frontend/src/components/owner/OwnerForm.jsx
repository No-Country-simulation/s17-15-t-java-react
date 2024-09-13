import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';

function OwnerForm({ modal, toggle, onSave, objOwner = {}, isEdit = false }) {
    const [ownerName, setOwnerName] = useState('');
    const [ownerLastname, setOwnerLastname] = useState('');
    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');

    useEffect(() => {
        if (isEdit && objOwner) {
            setOwnerName(objOwner.name || '');
            setOwnerLastname(objOwner.lastname || '');
            setPhone(objOwner.phone || '');
            setEmail(objOwner.email || '');
            setAddress(objOwner.address || '');
        }
    }, [isEdit, objOwner]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "ownerName") setOwnerName(value);
        if (name === "ownerLastname") setOwnerLastname(value);
        if (name === "phone") setPhone(value);
        if (name === "email") setEmail(value);
        if (name === "address") setAddress(value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const ownerData = {
            name: ownerName,
            lastname: ownerLastname,
            phone: phone,
            email: email,
            address: address
        };

        // Enviar el objeto JSON en lugar de FormData
        onSave(ownerData, isEdit ? objOwner.id : null);
        toggle(false);
    };

    if (!modal) {
        return null;
    }

    return (
        <div className="fixed top-[100px] inset-0 z-50 flex items-center justify-center overflow-y-auto bg-white bg-opacity-100">
            <div className="bg-neutral w-full max-w-lg">
                {/* <div className='bg-black w-full h-24'>

                    
                </div> */}
                <div className="flex justify-between items-center pb-5">
                    <div>
                        <h2 className="text-lg font-semibold">{isEdit ? 'Actualizar' : 'Nuevo'} Registro</h2>
                        <p className='text-xs italic'>Registra al nuevo cliente y continuá para cargar una mascota.</p>
                    </div>
                    {/* <button onClick={toggle} className="btn btn-link">
                        <FaTimes size={20} />
                    </button> */}
                </div>
                <form onSubmit={handleSubmit} className="p-4 border-2 rounded-lg">
                    <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-4 gap-y-4">
                        <div className="">
                            <label className="block text-sm font-medium mb-1">Nombre completo:</label>
                            <input
                                type="text"
                                name="ownerName"
                                value={ownerName}
                                onChange={handleChange}
                                className="input input-bordered input-warning input-sm w-full"
                                placeholder="Nombre"
                                required
                            />
                        </div>
                        <div className="">
                            <label className="block text-sm font-medium mb-1">Apellido:</label>
                            <input
                                type="text"
                                name="ownerLastname"
                                value={ownerLastname}
                                onChange={handleChange}
                                className="input input-bordered input-warning input-sm w-full"
                                placeholder="Apellido"
                                required
                            />
                        </div>
                        <div className="">
                            <label className="block text-sm font-medium mb-1">Correo Electrónico:</label>
                            <input
                                type="text"
                                name="email"
                                value={email}
                                onChange={handleChange}
                                className="input input-bordered input-warning input-sm w-full"
                                placeholder="Correo elétronico"
                                required
                            />
                        </div>
                        <div className="">
                            <label className="block text-sm font-medium mb-1">Teléfono celular:</label>
                            <input
                                type="number"
                                name="phone"
                                value={phone}
                                onChange={handleChange}
                                className="input input-bordered input-warning input-sm w-full"
                                placeholder="Teléfono celular"
                                required
                            />
                        </div>

                        <div className=" sm:col-span-2">
                            <label className="block text-sm font-medium mb-1">Dirección:</label>
                            <input
                                type="text"
                                name="address"
                                value={address}
                                onChange={handleChange}
                                className="input input-bordered input-warning input-sm w-full"
                                placeholder="Enter address"
                                required
                            />
                        </div>
                    </div>
                    <div className="flex justify-end gap-3 mt-3 pb-2">
                        <button
                            onClick={toggle}
                            type="button"
                            className="btn btn-sm  "
                        >
                            Cancelar
                        </button>
                        <button
                            type="submit"
                            className="btn btn-sm btn-primary"
                        >
                            {isEdit ? 'Actualizar' : 'Continuar'}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default OwnerForm;
