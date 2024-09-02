import React from 'react';
import { Link } from 'react-router-dom';

function PetCard({ pet }) {
  return (
    <li>
      <Link to={`/pets/${pet.id}`} className="hover:bg-blue-500 hover:ring-blue-500 hover:shadow-md group rounded-md p-3 bg-black ring-1 ring-slate-800 shadow-sm">
        <dl className="grid sm:block lg:grid xl:block grid-cols-2 grid-rows-2 items-center">
          <div>
            <dt className="sr-only">Name</dt>
            <dd className="group-hover:text-white font-semibold text-slate-900">
              {pet.name}
            </dd>
          </div>
          <div>
            <dt className="sr-only">Species</dt>
            <dd className="group-hover:text-blue-200">{pet.species}</dd>
          </div>
          <div>
            <dt className="sr-only">Race</dt>
            <dd className="group-hover:text-blue-200">{pet.race}</dd>
          </div>
          <div>
            <dt className="sr-only">Details</dt>
            <dd className="group-hover:text-blue-200">{pet.details}</dd>
          </div>
        </dl>
      </Link>
    </li>
  );
}

export default PetCard;
