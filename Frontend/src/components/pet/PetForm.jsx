import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';
import Navbar from '../Navbar';
import { Link } from "react-router-dom"
import { TiArrowBack } from "react-icons/ti";

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
            // case 'castrated':
            //     setCastrated(type === 'checkbox' ? checked : value);
            //     break;
            case 'castrated':
                setCastrated(value === 'true');
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

        <div className="fixed inset-0 z-50 overflow-hidden bg-white bg-opacity-100">
            <Navbar className="w-full fixed top-0 left-0 z-50" />
            {isEdit && (
                <button
                onClick={() => navigate(-1)}
                className="pl-10 mt-4 font-bold flex flex-row"
            >
                <TiArrowBack />
                Volver
            </button>
        
        )}

            <div className="w-screen h-screen flex justify-center items-start overflow-x-hidden">
                {/* Ajuste de pt-20 para asegurar que el contenido no quede detrás del Navbar */}
                <div className="max-w-3xl w-full">
                    <div className="flex justify-center items-center pb-5 col-2 row-0">
                        <div>
                            {isEdit && (
                                <h2 className="text-center text-lg font-semibold" >
                                    Editar Mascota
                                </h2>
                            )}

                            {!isEdit && (
                                <div>
                                    <h2 className="text-center text-lg font-semibold" >
                                        Registrar Mascota
                                    </h2>

                                    <p className="text-xs italic">
                                        Asocia una mascota al propietario y haz click en "finalizar"
                                    </p></div>
                            )}
                        </div>
                        {/* <button onClick={toggle} className="btn btn-link">
            <FaTimes size={20} />
          </button> */}
                    </div>
                    <form onSubmit={handleSubmit} className="bg-primary bg-opacity-10 p-4 border-2 rounded-lg">
                        <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-4 gap-y-4">
                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Nombre:
                                </label>
                                <input
                                    type="text"
                                    name="petName"
                                    value={petName}
                                    onChange={handleChange}
                                    placeholder="Ingresar nombre"
                                    required
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>
                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Sexo:
                                </label>
                                <div className="join px-10">
                                    <label className="cursor-pointer">
                                        <input
                                            type="radio"
                                            name="sex"
                                            value="MACHO"
                                            checked={sex === 'MACHO'}
                                            onChange={handleChange}
                                            className="radio radio-sm"
                                        // defaultChecked
                                        />
                                        <span className="ml-2 text-xs">MACHO</span>
                                    </label>
                                    <label className="cursor-pointer ml-4">
                                        <input
                                            type="radio"
                                            name="sex"
                                            value="HEMBRA"
                                            checked={sex === 'HEMBRA'}
                                            onChange={handleChange}
                                            className="radio radio-sm"
                                        />
                                        <span className="ml-2 text-xs">HEMBRA</span>
                                    </label>
                                </div>
                            </div>
                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Especie:
                                </label>
                                <select
                                    name="species"
                                    value={species}
                                    onChange={handleChange}
                                    className="select select-bordered select-sm w-full max-w-full"
                                >
                                    <option value="PERRO">PERRO</option>
                                    <option value="GATO">GATO</option>
                                    <option value="REPTIL">REPTIL</option>
                                    <option value="AVE">AVE</option>
                                    <option value="TORTUGA">TORTUGA</option>
                                    <option value="OTRO">OTRO</option>
                                </select>
                            </div>


                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Raza:
                                </label>
                                <input
                                    type="text"
                                    name="race"
                                    value={race}
                                    onChange={handleChange}
                                    placeholder="Ingresar raza"
                                    required
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>


                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Fecha de Nacimiento:
                                </label>
                                <input
                                    type="date"
                                    name="birthdate"
                                    value={birthdate}
                                    onChange={handleChange}
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>



                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Alergias:
                                </label>
                                <input
                                    type="text"
                                    name="allergies"
                                    value={allergies}
                                    onChange={handleChange}
                                    placeholder="Ingresar alergias"
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>

                            {/* <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Castrado:
                                </label>
                                <input
                                    type="checkbox"
                                    name="castrated"
                                    checked={castrated}
                                    onChange={handleChange}
                                    className="checkbox checkbox-xs"
                                />
                            </div> */}
                            <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Castrado:
                                </label>
                                <select
                                    name="castrated"
                                    value={castrated}
                                    onChange={handleChange}
                                    className="select select-bordered select-xs"
                                >
                                    <option value="true">Sí</option>
                                    <option value="false">No</option>
                                </select>
                            </div>


                            <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Activo:
                                </label>
                                <input
                                    type="checkbox"
                                    name="active"
                                    checked={active}
                                    onChange={handleChange}
                                    className="checkbox checkbox-xs"
                                />
                            </div>
                        </div>

                        <div className="form-control w-full pb-2">
                            <label className="block text-sm font-medium mb-1 mt-1">
                                Detalles:
                            </label>
                            <textarea
                                name="details"
                                value={details}
                                onChange={handleChange}
                                placeholder="Ingresar detalles"
                                className="textarea textarea-xs textarea-bordered w-full"
                            />
                        </div>

                        <div className="flex justify-end gap-3 mt-3 pb-2">
                            {!isEdit && (<button onClick={toggle} type="button" className="btn btn-sm">
                                Cancelar
                            </button>)}

                            <button type="submit" className="btn btn-sm btn-primary">
                                {isEdit ? 'Guardar' : 'Finalizar'}
                            </button>
                        </div>
                    </form>
            
                </div>

            </div>
  
        </div>
    );
}

export default PetForm;

