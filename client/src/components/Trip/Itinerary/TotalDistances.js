import React, {useEffect, useState} from "react";

export default function TotalDistance(distances){

    const [totalDistance, setTotalDistance] = useState(0);

    useEffect(() => {
		calculateTotalDistance(distances, {setTotalDistance});
	}, [distances]);
    
    return [totalDistance, setTotalDistance];
}

function calculateTotalDistance(distances, {setTotalDistance}){
    let total = 0;
    for (let i = 0; i < distances.length; i++){
        total += distances[i];
    }
    setTotalDistance(total);
}
