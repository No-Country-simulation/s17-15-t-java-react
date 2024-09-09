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
        <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-black bg-opacity-50">
            <div className="bg-white rounded-lg shadow-lg w-full max-w-lg">
                <div className="flex justify-between items-center p-4 border-b bg-base-300 text-base-100">
                    <h2 className="text-xl font-semibold">{isEdit ? 'Actualizar' : 'Crear'} Propietario</h2>
                    <button onClick={toggle} className="text-base-100 hover:text-gray-700">
                        <FaTimes size={20} />
                    </button>
                </div>
                <form onSubmit={handleSubmit} className="p-4">
                    <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-medium mb-2">Nombre:</label>
                            <input
                                type="text"
                                name="ownerName"
                                value={ownerName}
                                onChange={handleChange}
                                className="input input-bordered input-warning w-full"
                                placeholder="Enter owner name"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-medium mb-2">Apellido:</label>
                            <input
                                type="text"
                                name="ownerLastname"
                                value={ownerLastname}
                                onChange={handleChange}
                                className="input input-bordered input-warning w-full"
                                placeholder="Enter owner lastname"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-medium mb-2">Telefono/Celular:</label>
                            <input
                                type="text"
                                name="phone"
                                value={phone}
                                onChange={handleChange}
                                className="input input-bordered input-warning w-full"
                                placeholder="Enter phone"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-medium mb-2">Email:</label>
                            <input
                                type="text"
                                name="email"
                                value={email}
                                onChange={handleChange}
                                className="input input-bordered input-warning w-full"
                                placeholder="Enter email"
                            />
                        </div>
                        <div className="mb-4 sm:col-span-2">
                            <label className="block text-gray-700 text-sm font-medium mb-2">Dirección:</label>
                            <input
                                type="text"
                                name="address"
                                value={address}
                                onChange={handleChange}
                                className="input input-bordered input-warning w-full"
                                placeholder="Enter address"
                            />
                        </div>
                    </div>
                    <div className="flex justify-end mt-4">
                        <button
                            type="submit"
                            className="btn btn-primary py-2 px-4 mr-2"
                        >
                            {isEdit ? 'Actualizar' : 'Continuar'}
                        </button>
                        <button
                            onClick={toggle}
                            type="button"
                            className="bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-600"
                        >
                            Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}    
export default OwnerForm;
