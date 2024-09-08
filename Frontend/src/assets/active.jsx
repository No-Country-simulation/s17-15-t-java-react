

const Active = (props) => {
  return (
    <svg
      width="20"
    className="flex items-center justify-center"
      viewBox="0 0 20 20"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <g filter="url(#filter0_d_259_861)">
        {
          // eslint-disable-next-line react/prop-types
          props.fill === "active" ? (
            <circle cx="10" cy="11" r="6" fill="#3AE319" />
          ):
          (
            <circle cx="10" cy="11" r="6" fill="#FF0000" />
          )
        }
        
      </g>
      
    </svg>
  );
};

export default Active;
