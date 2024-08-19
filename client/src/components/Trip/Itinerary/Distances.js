import React, { useEffect, useState } from 'react';
import { sendAPIRequest } from '../../../utils/restfulAPI';
import { LOG } from '../../../utils/constants';


export default function Distances(places, serverUrl) {
    const [ distances, setDistances ] = useState([]);

    useEffect(() => {
		sendDistancesRequest(places, {setDistances}, serverUrl);
	}, [places], serverUrl);
    return [distances, setDistances];
}

async function sendDistancesRequest(places, {setDistances}, serverUrl){
    const distancesResponse = await sendAPIRequest({ requestType: 'distances', earthRadius: 3959, places: places}, serverUrl);
    if (distancesResponse){
        setDistances(distancesResponse.distances);
    }
    else{
        LOG.error(`Distances request to ${serverUrl} failed. Check the log for more details.`, 'error');
    }
}



