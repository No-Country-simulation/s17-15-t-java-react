import React from 'react';
import { useParams } from 'react-router-dom';

function PetDetail() {
  const { id } = useParams(); //ID de la mascota desde la URL
  // const petId = parseInt(id, 10);


  return (
    // <div className="p-4 sm:px-8 sm:py-6 lg:p-4 xl:px-8 xl:py-6">
    //   <h2 className="font-semibold text-slate-900">{pet.name}</h2>
    //   <p><strong>Species:</strong> {pet.species}</p>
    //   <p><strong>Race:</strong> {pet.race}</p>
    //   <p><strong>Sex:</strong> {pet.sex}</p>
    //   <p><strong>Birthdate:</strong> {pet.birthdate}</p>
    //   <p><strong>Allergies:</strong> {pet.allergies}</p>
    //   <p><strong>Castrated:</strong> {pet.castrated ? "Yes" : "No"}</p>
    //   <p><strong>Alive:</strong> {pet.alive ? "Yes" : "No"}</p>
    //   <p><strong>Details:</strong> {pet.details}</p>
    // </div>
    <p className='text-center text-xl font-semibold p-10'> LA MASCOTA TIENE EL ID: {id}</p>
  );
}

export default PetDetail;
