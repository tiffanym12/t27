import { sendAPIRequest } from '../../../src/utils/restfulAPI';
import { LOG } from  '../../../src/utils/constants'

export default async function sendFindRequest(match, serverUrl){
    const findResponse = await sendAPIRequest({requestType: 'find', match: match, limit: 10}, serverUrl);
    if(findResponse){ 
        return findResponse.places;
    }
    else{
        LOG.error(`Find request to ${serverUrl} failed. Check the log for more details.`, 'error');
    }
}


