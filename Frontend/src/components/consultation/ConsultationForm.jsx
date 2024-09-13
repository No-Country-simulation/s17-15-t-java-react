import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';

function PetForm({ modal, toggle, onSave, objPet = {}, isEdit = false, idOwner = null }) {
    const [petName, setPetName] = useState('');
    const [race, setRace] = useState('');
    const [species, setSpecies] = useState('PERRO');
    const [birthdate, setBirthdate] = useState('');
    const [sex, setSex] = useState('MACHO');
    const [allergies, setAllergies] = useState('');
    const [castrated, setCastrated] = useState(false);
    const [active, setActive] = useState(true);
    const [details, setDetails] = useState('');
    const [ownerID, setOwnerID] = useState(0);

    useEffect(() => {
        if (isEdit && objPet) {
            setPetName(objPet.name || '');
            setRace(objPet.race || '');
            setSpecies(objPet.species || 'PERRO');
            setBirthdate(objPet.birthdate || '');
            setSex(objPet.sex || 'MACHO');
            setAllergies(objPet.allergies || '');
            setCastrated(objPet.castrated || false);
            setActive(objPet.active || true);
            setDetails(objPet.details || '');
            setOwnerID(objPet.owner_id || 0);
        } else {
            setOwnerID(idOwner);
        }
    }, [isEdit, objPet]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        switch (name) {
            case 'petName':
                setPetName(value);
                break;
            case 'race':
                setRace(value);
                break;
            case 'species':
                setSpecies(value);
                break;
            case 'birthdate':
                setBirthdate(value);
                break;
            case 'sex':
                setSex(value);
                break;
            case 'allergies':
                setAllergies(value);
                break;
            case 'castrated':
                setCastrated(type === 'checkbox' ? checked : value);
                break;
            case 'active':
                setActive(type === 'checkbox' ? checked : value);
                break;
            case 'details':
                setDetails(value);
                break;
            default:
                break;
        }
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
            active: active,
            details: details,
            owner_id: ownerID,
        };

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
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div className="form-control">
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
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Raza:</span>
                            </div>
                            <input
                                type="text"
                                name="race"
                                value={race}
                                onChange={handleChange}
                                placeholder="Ingresar raza"
                                required
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Especie:</span>
                            </div>
                            <select
                                name="species"
                                value={species}
                                onChange={handleChange}
                                className="input input-bordered w-full"
                            >
                                <option value="PERRO">PERRO</option>
                                <option value="GATO">GATO</option>
                                <option value="OTRO">OTRO</option>
                            </select>
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Fecha de Nacimiento:</span>
                            </div>
                            <input
                                type="date"
                                name="birthdate"
                                value={birthdate}
                                onChange={handleChange}
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Sexo:</span>
                            </div>
                            <div className="join">
                                <label className="cursor-pointer">
                                    <input
                                        type="radio"
                                        name="sex"
                                        value="MACHO"
                                        checked={sex === 'MACHO'}
                                        onChange={handleChange}
                                        className="radio"
                                    />
                                    <span className="ml-2">MACHO</span>
                                </label>
                                <label className="cursor-pointer ml-4">
                                    <input
                                        type="radio"
                                        name="sex"
                                        value="HEMBRA"
                                        checked={sex === 'HEMBRA'}
                                        onChange={handleChange}
                                        className="radio"
                                    />
                                    <span className="ml-2">HEMBRA</span>
                                </label>
                            </div>
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Alergias:</span>
                            </div>
                            <input
                                type="text"
                                name="allergies"
                                value={allergies}
                                onChange={handleChange}
                                placeholder="Ingresar alergias"
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Castrado:</span>
                            </div>
                            <input
                                type="checkbox"
                                name="castrated"
                                checked={castrated}
                                onChange={handleChange}
                                className="checkbox"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Activo:</span>
                            </div>
                            <input
                                type="checkbox"
                                name="active"
                                checked={active}
                                onChange={handleChange}
                                className="checkbox"
                            />
                        </div>
                    </div>

                    <div className="form-control w-full pb-2">
                        <div className="label">
                            <span className="label-text font-semibold text-base-300">Detalles:</span>
                        </div>
                        <textarea
                            name="details"
                            value={details}
                            onChange={handleChange}
                            placeholder="Ingresar detalles"
                            className="textarea textarea-bordered w-full"
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

export default PetForm;

