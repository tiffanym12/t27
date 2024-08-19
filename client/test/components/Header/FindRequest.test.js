import { beforeEach, describe, expect, jest, test } from "@jest/globals";
import { LOG } from '../../../src/utils/constants';
import sendFindRequest from "../../../src/components/Header/FindRequest";
import { MOCK_FIND_RESPONSE, VALID_FIND_RESPONSE} from "../../sharedMocks";

describe('FindRequest', () => {
    beforeEach(() => {
        jest.clearAllMocks;
        fetch.resetMocks();
        jest.spyOn(LOG, 'error').mockImplementation(()=>{});

    });

    test('ewolving: empty FindRequest', async () =>{
        fetch.mockResponse(VALID_FIND_RESPONSE);
        const emptyPlaces = await sendFindRequest('ThisDoesNotExistAndNeverWillInTheDatabase','http://localhost:31400');
        expect(emptyPlaces).toEqual([]);
    });

    test('ewolving: return one found place', async ()=>{
        fetch.mockResponse(MOCK_FIND_RESPONSE);
        const onePlace = await sendFindRequest('johnny','http://localhost:31400');
        expect(onePlace.length).toEqual(1);
    });

    test('ewolving: bad findRequest', async () =>{
        fetch.mockReject(new Error('Expected failed request'));
        const badRequest = await sendFindRequest('','ThisIsNotAURL:)');
        expect(LOG.error.mock.calls.length).toBeGreaterThanOrEqual(1);
    })
});