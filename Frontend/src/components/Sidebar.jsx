import SidebarMenu from "./SidbarMenu";
import { Link } from "react-router-dom";


import { RiHomeHeartFill } from "react-icons/ri";
import { FaUserDoctor } from "react-icons/fa6";
import { MdPets } from "react-icons/md";
import { FaLaptopMedical } from "react-icons/fa";
import { MdSnippetFolder } from "react-icons/md";
import { GiLoveHowl } from "react-icons/gi";
import { FaFileInvoiceDollar } from "react-icons/fa";
import { SiInformatica } from "react-icons/si";


function Sidebar({ appName }) {
    return (

        <div className="bg-primary bg-opacity-60">

            <SidebarMenu
                items={[
                    {
                        text: "Home",
                        url: "/",
                        icon: <RiHomeHeartFill size={20} />
                    },

                    {
                        text: "Veterinarians",
                        url: "/veterinarians",
                        icon: <FaUserDoctor size={20} />
                    },
                    {
                        text: "Owners",
                        url: "/owners",
                        icon: <GiLoveHowl size={20} />
                    },
                    {
                        text: "Pets",
                        url: "/pets",
                        icon: <MdPets size={20} />
                    },
                    {
                        text: "Appointments",
                        url: "/appointments",
                        icon: <FaLaptopMedical size={20} />
                    },
                    {
                        text: "Medical History",
                        url: "/medical-history",
                        icon: <MdSnippetFolder size={20} />
                    },
                    {
                        text: "Invoices",
                        url: "/invoices",
                        icon: <FaFileInvoiceDollar size={20} />
                    },

                    {
                        text: "About",
                        url: "/about",
                        icon: <SiInformatica size={20} />
                    },

                ]}
            />

        </div>


    );
}

export default Sidebar;