import { useState, useEffect } from 'react';

const useFetchData = (url) => {
  const [data, setData] = useState(null);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error(`Error: ${response.status}`);
        }
        const result = await response.json();
        setData(result);
      } catch (err) {
        console.log(err);
      } 
    };

    fetchData();
  }, [url]);

  return data;
};

export default useFetchData;