import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ConsultationList from '../components/consultation/ConsultationList';
import TreatmentList from '../components/treatment/TreatmentList';

function PetDetail2() {
  const { id } = useParams();
  const [pet, setPet] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const baseURL = `https://veterinaria-bef3.onrender.com/pet/${id}`;

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
        const response = await fetch(baseURL);
        if (!response.ok) {
          throw new Error('Failed to fetch pet details');
        }
        const data = await response.json();
        setPet(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchPetDetails();
  }, [baseURL]);

  if (loading) return <p className="text-center">Loading...</p>;
  if (error) return <p className="text-center text-red-500">Error: {error}</p>;
  if (!pet) return <p className="text-center">No pet data found</p>;

  return (
    <>
      <p className='text-2xl font-bold text-center p-5'>Mascota:</p>
      <div className='flex items-center justify-center '>
        <div className="card card-side bg-base-200 shadow-xl">
          <figure className='p-5'>
            <img
              src={getSpeciesImage(pet.species)}
              alt=""
              className="w-32 h-32 object-cover mx-auto border-2 border-primary rounded-lg"
            />
          </figure>
          <div className="card-body">
            <h2 className="card-title">{pet.name}</h2>
            <div className='grid grid-cols-2 gap-x-3'>
              <p><strong>Species:</strong> {pet.species}</p>
              <p><strong>Race:</strong> {pet.race}</p>
              <p><strong>Sex:</strong> {pet.sex}</p>
              <p><strong>Birthdate:</strong> {pet.birthdate}</p>
              <p><strong>Allergies:</strong> {pet.allergies}</p>
              <p><strong>Castrated:</strong> {pet.castrated ? "Yes" : "No"}</p>
              <p><strong>Active:</strong> {pet.active ? "Yes" : "No"}</p>
              <p><strong>Details:</strong> {pet.details}</p>
            </div>

            {/* <div className="card-actions justify-end">
              <button className="btn btn-primary">Watch</button>
            </div> */}
          </div>
        </div>
      </div>

      <p className='text-2xl font-bold text-center p-5'>Otro titulo:</p>
      <div role="tablist" className="tabs tabs-lifted px-16 p-5">
        <input type="radio" name="my_tabs_2" role="tab" className="tab" aria-label="Consultas" defaultChecked />
        <div role="tabpanel" className="tab-content bg-base-100 border-base-300 rounded-box p-6">
          <ConsultationList id={id} />
        </div>

        <input
          type="radio"
          name="my_tabs_2"
          role="tab"
          className="tab"
          aria-label="Diagnosticos"
        />
        <div role="tabpanel" className="tab-content bg-base-100 border-base-300 rounded-box p-6">
          <TreatmentList id={id}/>
        </div>

        <input type="radio" name="my_tabs_2" role="tab" className="tab" aria-label="Tab 3" />
        <div role="tabpanel" className="tab-content bg-base-100 border-base-300 rounded-box p-6">
          Tab content 3
        </div>
      </div>
    </>
  );
}

export default PetDetail2;
