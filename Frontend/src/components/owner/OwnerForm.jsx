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
                <div className="flex justify-between items-center p-4 border-b">
                    <h2 className="text-xl font-semibold">{isEdit ? 'Update' : 'Create'} Owner</h2>
                    <button onClick={toggle} className="text-gray-500 hover:text-gray-700">
                        <FaTimes size={20} />
                    </button>
                </div>
                <form onSubmit={handleSubmit} className="p-4">
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-medium mb-2">Nombre:</label>
                        <input
                            type="text"
                            name="ownerName"
                            value={ownerName}
                            onChange={handleChange}
                            className="input input-bordered input-warning w-full max-w-xs"
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
                            className="input input-bordered input-warning w-full max-w-xs"
                            placeholder="Enter owner lastname"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-medium mb-2">Celular:</label>
                        <input
                            type="text"
                            name="phone"
                            value={phone}
                            onChange={handleChange}
                            className="input input-bordered input-warning w-full max-w-xs"
                            placeholder="Enter phone"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-medium mb-2">Email</label>
                        <input
                            type="text"
                            name="email"
                            value={email}
                            onChange={handleChange}
                            className="input input-bordered input-warning w-full max-w-xs"
                            placeholder="Enter email"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-medium mb-2">Direccion: </label>
                        <input
                            type="text"
                            name="address"
                            value={address}
                            onChange={handleChange}
                            className="input input-bordered input-warning w-full max-w-xs"
                            placeholder="Enter address"
                        />
                    </div>

                    <div className="flex justify-end">
                        <button
                            type="submit"
                            className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 mr-2"
                        >
                            {isEdit ? 'Update' : 'Save'}
                        </button>
                        <button
                            onClick={toggle}
                            type="button"
                            className="bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-600"
                        >
                            Cancel
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default OwnerForm;
