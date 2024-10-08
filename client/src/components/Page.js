import React, { useEffect, useState } from 'react';
import { Collapse } from 'reactstrap';
import Header from './Header/Header';
import About from './About/About';
import Planner from './Trip/Planner';
import { useToggle } from '../hooks/useToggle';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';
import { usePlaces } from '../hooks/usePlaces';
import Distances from './Trip/Itinerary/Distances'
import TotalDistance from './Trip/Itinerary/TotalDistances';

export default function Page(props) {
	const [showAbout, toggleAbout] = useToggle(false);
	const [serverSettings, processServerConfigSuccess] = useServerSettings(
		props.showMessage
	);
	const { places, selectedIndex, placeActions } = usePlaces();
	const [tripName, setTripName] = useState('My Trip');
	const [distances] = Distances(places, serverSettings.serverUrl);
	const [totalDistance] = TotalDistance(distances);

	return (
		<>
			<Header
				toggleAbout={toggleAbout}
				showAbout={showAbout}
				placeActions={placeActions}
				disableRemoveAll={!places?.length}
				serverSettings={serverSettings}
				processServerConfigSuccess={processServerConfigSuccess}
				setTripName={setTripName}
			/>
			<MainContentArea
				showAbout={showAbout}
				toggleAbout={toggleAbout}
				places={places}
				selectedIndex={selectedIndex}
				placeActions={placeActions}
				tripName={tripName}
				distances = {distances}
				totalDistance={totalDistance}
			/>
		</>
	);
}

function MainContentArea(props) {
	return (
		<div className='body'>
			<Collapse isOpen={props.showAbout}>
				<About closePage={props.toggleAbout} />
			</Collapse>
			<Collapse isOpen={!props.showAbout} data-testid='planner-collapse'>
				<Planner
					places={props.places}
					selectedIndex={props.selectedIndex}
					placeActions={props.placeActions}
					tripName={props.tripName}
					distances={props.distances}
					totalDistance={props.totalDistance}
				/>
			</Collapse>
		</div>
	);
}

function useServerSettings(showMessage) {
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [serverConfig, setServerConfig] = useState(null);

	useEffect(() => {
		sendConfigRequest();
	}, []);

	function processServerConfigSuccess(config, url) {
		LOG.info('Switching to Server:', url);
		setServerConfig(config);
		setServerUrl(url);
	}

	async function sendConfigRequest() {
		const configResponse = await sendAPIRequest({ requestType: 'config' },serverUrl);
		if (configResponse) {
			processServerConfigSuccess(configResponse, serverUrl);
		} else {
			setServerConfig(null);
			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, 'error');
		}
	}

	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess,];
}
