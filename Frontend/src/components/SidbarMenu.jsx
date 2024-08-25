import { Link } from "react-router-dom";

function SidebarMenu({ items }) {
    return (

        <ul className="menu bg-base-200 text-base-content min-h-full min-w-[195px] p-4">
            {items.map((item, index) => (
                <button key={index} className="">
                    <li>
                        <Link
                            to={item.url}
                            className=""
                        >
                            {item.icon}
                            <span className="ms-3">{item.text}</span>
                        </Link>

                    </li>
                </button>

            ))}
        </ul>


    );
}

export default SidebarMenu;