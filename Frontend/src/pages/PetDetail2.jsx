import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ConsultationList from '../components/consultation/ConsultationList';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import { MdOutlineEdit } from "react-icons/md";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import TreatmentList from '../components/treatment/TreatmentList';

function PetDetail2() {
  const { id } = useParams();
  const [pet, setPet] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const petURL = `https://veterinaria-bef3.onrender.com/pet/${id}`;

  const [owner, setOwner] = useState(null);

  const navigate = useNavigate();


  const getSpeciesImage = (species) => {
    switch (species.toLowerCase()) {
      case 'perro':
        return '/avatar_mascota/Perro.png';
      case 'gato':
        return '/avatar_mascota/Gato.png';
      case 'ave':
        return '/avatar_mascota/Ave.png';
      case 'reptil':
        return '/avatar_mascota/Reptil.png';
      case 'tortuga':
        return '/avatar_mascota/Tortuga.png';
      default:
        return '/avatar_mascota/default.png'; // Imagen por defecto
    }
  };

  useEffect(() => {
    const fetchPetDetails = async () => {
      try {
        setLoading(true);

        const petResponse = await fetch(petURL);
        if (!petResponse.ok) {
          throw new Error('Failed to fetch pet details');
        }
        const petData = await petResponse.json();
        setPet(petData);
        setLoading(false);

        // Fetch propietario
        const ownerResponse = await fetch(`https://veterinaria-bef3.onrender.com/owner/${petData.owner_id}`);
        if (!ownerResponse.ok) {
          throw new Error('Error fetch owner');
        }
        const ownerData = await ownerResponse.json();
        setOwner(ownerData);


      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
      finally {
        setLoading(false);
      }
    };

    fetchPetDetails();
  }, [petURL, id]);

  if (loading) return <p className="text-center">Cargando...</p>;
  if (error) return <p className="text-center text-red-500">Error: {error}</p>;
  if (!pet) return <p className="text-center">No pet data found</p>;
  if (!owner) return <p className="text-center">No owner data found</p>;

  return (
    <>
      <div className='flex justify-end mr-10'>
        <button
          className="mt-3 pr-3 btn bg-orange-400 bg-opacity-90 text-white p-2 inline-flex items-center justify-center  shadow-xl"
          onClick={() => navigate(`/consulta/crear/${id}`)}
        >
          <span className='font-semibold pr-1'>Crear Consulta</span>
          {/* <FaUserPlus className="font-semibold mr-2" size={20} /> */}
          <img src="/calendar.svg" className="font-semibold w-6 h-6" alt="plus" />

        </button>
      </div>
      <p className='text-2xl font-bold text-center p-5'>Mascota:</p>
      <div className='flex items-center justify-center '>
        <div className="card card-side bg-primary bg-opacity-10 shadow-2xl">
          <figure className='p-5'>
            <img
              src={getSpeciesImage(pet.species)}
              alt=""
              className="w-32 h-32 object-cover mx-auto border-2 border-primary rounded-lg"
            />
          </figure>
          <div className="card-body">

            {/* <h2 className="card-title font-extrabold text-primary ">{pet.name}</h2> */}
            <div className='grid grid-cols-2 gap-x-3'>
              <h2 className="card-title font-extrabold text-primary text-opacity-80">{pet.name}</h2>
              <h2 className="card-title font-extrabold text-primary ">#{pet.id}</h2>
              <p><strong>Nombre Propietario:</strong> {owner.name} {owner.lastname}</p>
              <p><strong>Sexo:</strong> {pet.sex}</p>
              <p><strong>Especie:</strong> {pet.species}</p>
              <p><strong>Alergias:</strong> {pet.allergies}</p>
              <p><strong>Raza:</strong> {pet.race}</p>
              <p><strong>Castrado:</strong> {pet.castrated ? "Sí" : "No"}</p>

              <p>
                <strong>Fecha de Nacimiento:</strong> {new Date(pet.birthdate).toLocaleDateString('es-ES', {
                  day: '2-digit',
                  month: '2-digit',
                  year: 'numeric',
                })}
              </p>

              <p>
                <strong>Estado:</strong> {pet.active ? "Activo" : "No Activo"}
                <span
                  className={`inline-block w-3 h-3 rounded-full ml-2 shadow-xl ${pet.active ? 'bg-green-500' : 'bg-red-500'}`}
                />
              </p>
              {/* <p><strong>Details:</strong> {pet.details}</p> */}
            </div>
            <Link to={`/mascota/editar/${id}`} >
              <div className="tooltip tooltip-top btn btn-circle btn-sm absolute top-3 right-3 flex space-x-2 bg-base-300  bg-opacity-100 border-base-300" data-tip="Editar">
                <MdOutlineEdit
                  className="text-xl cursor-pointer text-white"
                // onClick={() => setModal(true)}
                // onClick={() => {

                //     navigate("/mascota/editar/" + id)
                // }}
                />

                {/* <FaTrashAlt
                            className="text-xl cursor-pointer text-red-700"
                            onClick={handleDelete}

                            size={15}

                        /> */}
              </div>
            </Link>
            {/* <div className="card-actions justify-end">
              <button className="btn btn-primary">Watch</button>
            </div> */}
          </div>
        </div>



      </div>


      <Link to={`/historia-clinica/${id}`} className='text-center'>
        <p className='text-2xl font-bold text-center p-5'>Historial Medico:</p>
      </Link>

      <div role="tablist" className="tabs tabs-bordered px-16 p-5 ">
        <input type="radio" name="my_tabs_1" role="tab" className="tab [--tab-bg:orange]" aria-label="Consultas" defaultChecked />
        <div role="tabpanel" className="tab-content p-10 bg-primary bg-opacity-10">
          <ConsultationList id={id} />
        </div>

        <input type="radio" name="my_tabs_1" role="tab" className="tab [--tab-bg:orange]" aria-label="Diagnosticos" />
        <div role="tabpanel" className="tab-content p-10 bg-primary bg-opacity-10">
        <TreatmentList id={id}/>
        </div>

        <input type="radio" name="my_tabs_1" role="tab" className="tab" aria-label="Estudios Complementarios" />
        <div role="tabpanel" className="tab-content p-10 bg-primary bg-opacity-10">
          Tab content 3
        </div>

        <input type="radio" name="my_tabs_1" role="tab" className="tab" aria-label="Cirugías " />
        <div role="tabpanel" className="tab-content p-10 bg-primary bg-opacity-10">
          Tab content 4
        </div>
      </div>
    </>
  );
}

export default PetDetail2;
