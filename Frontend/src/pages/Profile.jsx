// import logo from "../assets/logo.png";
import mascot from "../assets/mascot.jpg";
import crearlogo from "../assets/crearlogo.png";
import pencil from "../assets/bxpencil.png";
import Active from "../assets/active";
// import loginicon from "../assets/loginicon.png";
import Navbar from "../components/Navbar";
// import useFetchData from "../hooks/useMascot";

function Profile() {
  const data = [
    {
      id: 1,
      name: "Emir",
      race: "caniche",
      species: "PERRO",
      owner_name: "Maria",
      birthdate: "2018-08-01",
      sex: "MACHO",
      allergies: "no 10 caracteres",
      castrated: false,
      active: true,
      details: "stringstri",
      owner_id: 1,
    },
    {
      id: 2,
      name: "Emir",
      race: "caniche",
      species: "PERRO",
      owner_name: "Carl",
      birthdate: "2018-08-01",
      sex: "MACHO",
      allergies: "no 10 caracteres",
      castrated: false,
      active: true,
      details: "stringstri",
      owner_id: 1,
    },
  ];

  {
    console.log(data);
  }
  return (
    <div>
      {/* <div className="bg-base-300 flex items-center justify-between p-4">
        <div className="flex justify-center items-center flex-grow">
          <img src={logo} alt="logo" className="mx-auto" />
        </div>
        <div className="flex justify-end items-center">
          <img src={loginicon} alt="loginicon" />
        </div>
      </div> */}
      <Navbar />
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
            Crear consulta{" "}
            <img src={crearlogo} className="w-5" alt="crearlogo" />
          </button>
        </div>
      </div>
      <div className="flex justify-center items-center">
        <div className="bg-secondary-content rounded-lg m-5 w-3/5">
          <div className="relative flex justify-between items-center p-4">
            <div className="flex flex-col space-y-6">
              {data.map((user) => (
                <div
                  key={user.id}
                  className="flex justify-between w-full ml-6 space-x-8"
                >
                  {/* Primera columna de datos del usuario */}
                  <img src={mascot} alt="mascot" className="w-32 h-32 mr-4 " />
                  <div className="w-1/2">
                    <section>
                      <h3 className="text-lg font-bold">{user.name}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">
                        Nombre Propietario: {user.owner_name}
                      </h3>
                    </section>
                    <section>
                      <h3 className="text-md">Especie: {user.species}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">Raza: {user.race}</h3>
                    </section>
                    <section>
                      <h3 className="text-md">
                        Fecha nacimiento: {user.birthdate}
                      </h3>
                    </section>
                  </div>
                  <div className="w-1/2 ">
                    <section>
                      <h3>123456789</h3>
                    </section>
                    <section>
                      <h3>Sexo:{user.sex} </h3>
                    </section>
                    <section>
                      <h3>Alergias:{user.allergies} </h3>
                    </section>
                    <section>
                      {user.castrated ? (
                        <h3>Castrado: Si </h3>
                      ) : (
                        <h3>Castrado: No </h3>
                      )}
                    </section>
                    <section>
                      {user.active ? (
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
              ))}
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
