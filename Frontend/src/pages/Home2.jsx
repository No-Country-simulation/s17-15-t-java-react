import React, { useState } from 'react';
import PetCard from '../components/pet/PetCard';

function Home2() {
  const owners = [
    {
      id: 1,
      name: "John",
      lastname: "Doe",
      phone: "555-1234",
      email: "john.doe@example.com",
      address: "123 Main St",
      pets: [
        {
          id: 1,
          name: "Bobby",
          race: "Golden Retriever",
          species: "PERRO",
          birthdate: "2021-05-12",
          sex: "MASCULINO",
          allergies: "None",
          castrated: true,
          alive: true,
          details: "Very playful",
          owner_id: 1
        },
        {
          id: 2,
          name: "Whiskers",
          race: "Tabby",
          species: "GATO",
          birthdate: "2019-03-05",
          sex: "FEMENINO",
          allergies: "None",
          castrated: false,
          alive: true,
          details: "Likes to climb",
          owner_id: 1
        }
      ]
    },
    {
      id: 2,
      name: "Jane",
      lastname: "Smith",
      phone: "666-1234",
      email: "jane.smith@example.com",
      address: "123 Main St",
      pets: [
        {
          id: 3,
          name: "Buddy",
          race: "Labrador",
          species: "PERRO",
          birthdate: "2020-07-22",
          sex: "MASCULINO",
          allergies: "None",
          castrated: true,
          alive: true,
          details: "Loves swimming",
          owner_id: 2
        },
        {
          id: 4,
          name: "Mittens",
          race: "Siamese",
          species: "GATO",
          birthdate: "2018-12-10",
          sex: "FEMENINO",
          allergies: "Fish",
          castrated: false,
          alive: true,
          details: "Very curious",
          owner_id: 2
        }
      ]
    },
    // Otros dueños y mascotas
  ];

  const [searchTerm, setSearchTerm] = useState('');

  const filteredOwners = owners.filter(owner =>
    owner.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    owner.lastname.toLowerCase().includes(searchTerm.toLowerCase()) ||
    owner.pets.some(pet => pet.name.toLowerCase().includes(searchTerm.toLowerCase()))
  );

  return (
    <section className=''>
      <header className="bg-base-100 space-y-4 p-4 sm:px-8 sm:py-6 lg:p-4 xl:px-8 xl:py-6">
        <div className="flex items-center justify-between">
          <h2 className="font-semibold text-slate-900">.</h2>
          <a href="/new" className="hover:bg-blue-400 group flex items-center rounded-md bg-blue-500 text-white text-sm font-medium pl-2 pr-3 py-2 shadow-sm">
            <svg width="20" height="20" fill="currentColor" className="mr-2" aria-hidden="true">
              <path d="M10 5a1 1 0 0 1 1 1v3h3a1 1 0 1 1 0 2h-3v3a1 1 0 1 1-2 0v-3H6a1 1 0 1 1 0-2h3V6a1 1 0 0 1 1-1Z" />
            </svg>
            Agregar Dueño
          </a>
        </div>

        <label className="input input-bordered flex items-center gap-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 16 16"
            fill="currentColor"
            className="h-4 w-4 opacity-70">
            <path
              fillRule="evenodd"
              d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
              clipRule="evenodd" />
          </svg>
          <input
            type="text"
            aria-label="Filter pets"
            placeholder="Buscar dueño o mascota"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="grow"
          />
        </label>
        <div className="flex flex-row">
          <div className="form-control">
            <label className="label cursor-pointer">
              <span className="label-text px-1">Dueño</span>
              <input type="radio" name="radio-10" className="radio checked:bg-blue-500" defaultChecked />
            </label>
          </div>
          <div className="form-control">
            <label className="label cursor-pointer">
              <span className="label-text px-1">Mascota</span>
              <input type="radio" name="radio-10" className="radio checked:bg-red-500" />
            </label>
          </div>
        </div>
      </header>

      <div className='bg-base-100'>
        {filteredOwners.map((owner) => (
          <div key={owner.id} className="card-compact owner-section mb-8 px-10 bg-slate-800">
            <div className='card-body bg-slate-800 text-white rounded-lg'>
              <h3 className="card-title text-center">{owner.name} {owner.lastname}</h3>
              <div className="mb-4 grid-flow-col grid grid-cols-1 sm:grid-cols-3 gap-4">
                <p>Email: {owner.email}</p>
                <p>Tel/Cel: {owner.phone}</p>
                <p>Dirección: {owner.address}</p>
                <p>cantidad de mascotas: {owner.pets.length}</p>
              
              </div>

              <div className='flex justify-between'>
                <button className='btn btn-xs btn-primary opacity-80 text-base-100'> + Agregar mascota</button>
                <button className='btn btn-xs btn-primary opacity-80 text-base-100'> Editar</button>
              </div>
            </div>

            <ul className="p-4 sm:px-8 sm:pt-6 sm:pb-8 lg:p-4 xl:px-8 xl:pt-6 xl:pb-8 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-3 gap-4 text-sm leading-6">
              {owner.pets.map((pet) => (
                <PetCard key={pet.id} pet={pet} owner={owner} />
              ))}
            </ul>
          </div>
        ))}
        {filteredOwners.length === 0 && (
          <p>No se encontraron dueños o mascotas.</p>
        )}
      </div>
    </section>
  );
}

export default Home2;
