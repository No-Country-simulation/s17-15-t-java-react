// import logo from "../assets/logo.png";
import mascot from "../assets/mascot.jpg";
import crearlogo from "../assets/crearlogo.png";
import pencil from "../assets/bxpencil.png";
import Active from "../assets/active";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";


function Profile() {
  const { id } = useParams();
  const [pet, setPet] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const baseURL = "https://veterinaria-bef3.onrender.com/pet/{id}"
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
    <div>

   
      <div className="flex justify-between items-center w-full">
        <button className="ml-4 text-warning-content italic font-semibold">
          Volver
        </button>
        <div className="flex-1 flex justify-center">
          <div className="flex flex-col items-center">
            <h1 className="text-2xl text-warning-content font-bold">Mascota</h1>
          </div>
        </div>

        <div className="mr-4 flex justify-center items-center bg-primary rounded-md space-x-5 mt-3 h-8 w-36">
          <button className="text-white text-sm flex items-center justify-center">
            Crear consulta
            <img src={crearlogo} className="w-5" alt="crearlogo" />
          </button>
        </div>
      </div>
      <div className="flex justify-center items-center">
        <div className="bg-secondary-content rounded-lg m-5 w-3/5">
          <div className="relative flex justify-between items-center p-4">
            <div className="flex flex-col space-y-6">
             
                <div
               
                  className="flex justify-between w-full ml-6 space-x-8"
                >
                  {/* Primera columna de datos del usuario */}
                  <img src={mascot} alt="mascot" className="w-32 h-32 mr-4 " />
                  <div className="w-1/2">
                    <section>
                      <h3 className="text-lg font-bold">{pet.name}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">
                        Nombre Propietario: {pet.owner_name}
                      </h3>
                    </section>
                    <section>
                      <h3 className="text-md">Especie: {pet.species}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">Raza: {pet.race}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">
                        Fecha nacimiento: {pet.birthdate}
                      </h3>
                    </section>
                  </div>
                  <div className="w-1/2 ">
                    <section>
                      <h3>123456789</h3>
                    </section>
                    <section>
                      <h3>Sexo:{pet.sex} </h3>
                    </section>
                    <section>
                      <h3>Alergias:{pet.allergies} </h3>
                    </section>
                    <section>
                      {pet.castrated ? (
                        <h3>Castrado: Si </h3>
                      ) : (
                        <h3>Castrado: No </h3>
                      )}
                    </section>
                    <section>
                      {pet.active ? (
                        <h3 className="flex items-center space-x-2">
                          Estado: Activo <Active fill="active" />
                        </h3>
                      ) : (
                        <h3 className="flex items-center space-x-2">
                          Estado: Inactivo <Active fill="inactive" />
                        </h3>
                      )}
                    </section>
                  </div>
                </div>
          
            </div>
            <div className="absolute top-0 right-0 m-4 ">
              <button>
                <img src={pencil} alt="pencil" className="w-10" />
              </button>
            </div>
          </div>

          <div className="flex justify-center items-center mb-9 mt-9">
            <button className="bg-primary rounded-lg w-40 h-11">
              Historial medico
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Profile;
