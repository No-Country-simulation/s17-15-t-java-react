import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';


function PetForm({ modal, toggle, onSave, objPet = {}, isEdit = false, idOwner = null }) {
    const [petName, setPetName] = useState('');
    const [race, setRace] = useState('');
    const [species, setSpecies] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [sex, setSex] = useState('')
    const [allergies, setAllergies] = useState('')
    const [castrated, setCastrated] = useState(false)
    const [active, setActive] = useState(true)
    const [details, setDetails] = useState('')
    const [ownerID, setOwnerID] = useState(0)

    useEffect(() => {
        if (isEdit && objPet) {
            setPetName(objPet.name || '');
            setRace(objPet.race || '');
            setSpecies(objPet.species || 'PERRO');
            setBirthdate(objPet.birthdate || '');
            setSex(objPet.sex || 'MACHO');
            setAllergies(objPet.allergies || '');
            setCastrated(objPet.castrated || false);
            setActive(objPet.active || true)
            setDetails(objPet.active || '')
            setOwnerID(objPet.owner_id || 0)
        } else {
            setOwnerID(idOwner)
        }
    }, [isEdit, objPet]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "petName") setPetName(value);
        if (name === "race") setRace(value);
        if (name === "species") setSpecies(value);
        if (name === "birthdate") setBirthdate(value);
        if (name === "sex") setSex(value);
        if (name === "allergies") setAllergies(value);
        if (name === "castrated") setCastrated(value);
        if (name === "active") setActive(value);
        if (name === "details") setDetails(value);

    };


    const handleSubmit = (e) => {
        e.preventDefault();
        const petData = {
            name: petName,
            race: race,
            species: species,
            birthdate: birthdate,
            sex: sex,
            allergies: allergies,
            castrated: castrated,
            castrated: active,
            details: details,
            owner_id: ownerID
        }

        onSave(petData, isEdit ? objPet.id : null);
        toggle(false);
    };

    if (!modal) {
        return null;
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-black bg-opacity-50">
            <div className="bg-white rounded-lg shadow-lg w-full max-w-lg">
                <div className="flex justify-between items-center p-4 border-b">
                    <h2 className="text-xl font-semibold">{isEdit ? 'Update' : 'Create'} Mascotas</h2>
                    <button onClick={toggle} className="text-gray-500 hover:text-gray-700">
                        <FaTimes size={20} />
                    </button>
                </div>
                <form onSubmit={handleSubmit} className="px-4 pb-4">

                    <label className="form-control w-full max-w-xs pb-2">
                        <div className="label">
                            <span className="label-text font-semibold text-base-300">Nombre:</span>
                            <span className="label-text-alt text-red-500">Requerido</span>
                        </div>
                        <input
                            type="text"
                            name="petName"
                            value={petName}
                            onChange={handleChange}
                            placeholder="Ingresar nombre"
                            required
                            className="input input-bordered w-full max-w-xs"
                        />
                    </label>

                    <label className="form-control w-full max-w-xs pb-2">
                        <div className="label">
                            <span className="label-text font-semibold text-base-300">Raza:</span>
                            {/* <span className="label-text-alt text-red-500">Requerido</span> */}
                        </div>
                        <input
                            type="text"
                            name="race"
                            value={race}
                            onChange={handleChange}
                            placeholder="Ingresar raza"
                            required
                            className="input input-bordered w-full max-w-xs"
                        />
                    </label>

                    <div className="join">
                        <label className="block text-gray-700 text-sm font-medium mb-2 pr-2">Sexo:</label>
                        <input className="join-item btn" type="radio" name="options" aria-label="MACHO" />
                        <input className="join-item btn" type="radio" name="options" aria-label="HEMBRA" />
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

export default PetForm;