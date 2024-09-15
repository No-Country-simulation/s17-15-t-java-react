import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ClinicHistory from '../components/ClinicHistory/ClinicHistory';

function ClinicHistoryPage () {
  const { id } = useParams();
    
 

  return (
    // <div className="p-6 bg-white shadow-md rounded-lg">

    // </div>
    <main>
      <ClinicHistory petId={id} />
    </main>
  );
};

export default ClinicHistoryPage;
