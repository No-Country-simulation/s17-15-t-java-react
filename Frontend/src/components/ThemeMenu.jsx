function ThemeMenu() {
    const themes = [
        "default",
        "light",
        "dark",
        // "cupcake",
        // "bumblebee",
        // "emerald",
        // "corporate",
        "synthwave",
        // "retro",
        // "cyberpunk",
        // "valentine",
        "halloween",
        // "garden",
        // "forest",
        "aqua",
        "lofi",
        // "pastel",
        // "fantasy",
        // "wireframe",
        "black",
        "luxury",
        "dracula",
        "cmyk",
        // "autumn",
        // "business",
        // "acid",
        // "lemonade",
        // "night",
        "coffee",
        // "winter",
        // "dim",
        // "nord",
        // "sunset"
    ];

    return (
        <div className="dropdown">
            <div tabIndex={1} role="button" className="btn btn-blue btn-sm m-1 bg-base-300 opacity-80  border-opacity-60">
                Theme
                <svg
                    width="12px"
                    height="12px"
                    className="inline-block h-2 w-2 fill-current opacity-60"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 2048 2048">
                    <path d="M1799 349l242 241-1017 1017L7 590l242-241 775 775 775-775z"></path>
                </svg>
            </div>
            <ul tabIndex={1} className="dropdown-content bg-base-200 rounded-box z-[51] p-2 shadow-2xl">
                {themes.map((theme) => (
                    <li key={theme}>
                        <input
                            type="radio"
                            name="theme-dropdown"
                            className="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                            aria-label={theme.charAt(0).toUpperCase() + theme.slice(1)}
                            value={theme} />
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default ThemeMenu;
