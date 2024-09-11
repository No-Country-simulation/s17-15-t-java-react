import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

function PetDetail2() {
  const { id } = useParams();
  const [pet, setPet] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const baseURL = `https://veterinaria-bef3.onrender.com/pet/${id}`;

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
      <div className="p-4 sm:px-8 sm:py-6 lg:p-4 xl:px-8 xl:py-6">
        <h2 className="font-semibold text-slate-900">{pet.name}</h2>
        <p><strong>Species:</strong> {pet.species}</p>
        <p><strong>Race:</strong> {pet.race}</p>
        <p><strong>Sex:</strong> {pet.sex}</p>
        <p><strong>Birthdate:</strong> {pet.birthdate}</p>
        <p><strong>Allergies:</strong> {pet.allergies}</p>
        <p><strong>Castrated:</strong> {pet.castrated ? "Yes" : "No"}</p>
        <p><strong>Active:</strong> {pet.active ? "Yes" : "No"}</p>
        <p><strong>Details:</strong> {pet.details}</p>
      </div>
      <div role="tablist" className="tabs tabs-lifted">
        <input type="radio" name="my_tabs_2" role="tab" className="tab" aria-label="Consultas" />
        <div role="tabpanel" className="tab-content bg-base-100 border-base-300 rounded-box p-6">
          Tab content 1
        </div>

        <input
          type="radio"
          name="my_tabs_2"
          role="tab"
          className="tab"
          aria-label="Diagnosticos"
          defaultChecked />
        <div role="tabpanel" className="tab-content bg-base-100 border-base-300 rounded-box p-6">
          Tab content 2
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
