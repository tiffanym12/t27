import React, { useEffect, useState } from 'react';
import { Place } from '../../models/place.model';
import sendFindRequest from './FindRequest';
import {
	Button,
	Col,
	Modal,
	ModalBody,
	ModalHeader,
	Input,
	Collapse,
	ModalFooter,
} from 'reactstrap';
import Coordinates from 'coordinate-parser';
import { reverseGeocode } from '../../utils/reverseGeocode';

export default function AddPlace(props) {
	const [foundPlace, setFoundPlace] = useState(); const [foundPlace2, setFoundPlace2] = useState(); const [foundPlace3, setFoundPlace3] = useState(); const [foundPlace4, setFoundPlace4] = useState(); const [foundPlace5, setFoundPlace5] = useState(); const [coordString, setCoordString] = useState('');
	return (
		<Modal isOpen={props.isOpen} toggle={props.toggleAddPlace}>
			<AddPlaceHeader toggleAddPlace={props.toggleAddPlace}/> 
			<PlaceSearch
				foundPlace={foundPlace} setFoundPlace={setFoundPlace} foundPlace2={foundPlace2} setFoundPlace2={setFoundPlace2} foundPlace3={foundPlace3} setFoundPlace3={setFoundPlace3} foundPlace4={foundPlace4} setFoundPlace4={setFoundPlace4} foundPlace5={foundPlace5} setFoundPlace5={setFoundPlace5} coordString={coordString} setCoordString={setCoordString} serverSettings={props.serverSettings}
			/>
			<ModalFooter>
			<AddPlaceFooter
				append={props.append} testid= 'add-place-button' buttonText= 'Add Place 1' foundPlace={foundPlace} coordString={coordString} setCoordString={setCoordString}/>
			<AddPlaceFooter
				append={props.append} testid= 'add-place-button2' buttonText= 'Add Place 2' foundPlace={foundPlace2} coordString={coordString} setCoordString={setCoordString}/>
			<AddPlaceFooter
				append={props.append} testid= 'add-place-button3' buttonText= 'Add Place 3' foundPlace={foundPlace3} coordString={coordString} setCoordString={setCoordString}/>
			<AddPlaceFooter
				append={props.append} testid= 'add-place-button4' buttonText= 'Add Place 4' foundPlace={foundPlace4} coordString={coordString} setCoordString={setCoordString}/>
			<AddPlaceFooter
				append={props.append} testid= 'add-place-button5' buttonText= 'Add Place 5' foundPlace={foundPlace5} coordString={coordString} setCoordString={setCoordString}/>
			<ClearSearch
				setCoordString={setCoordString} coordString={coordString}/>
			</ModalFooter>
		</Modal>
	);
}

function AddPlaceHeader(props) {
	return (
		<ModalHeader className='ml-2' toggle={props.toggleAddPlace}>
			Add a Place
		</ModalHeader>
	);
}

function PlaceSearch(props) {
	useEffect(() => {
		clearPlaces(props);
		verifyCoordinates(props.coordString, props.setFoundPlace);
		if (props.coordString.length > 2) {
			try {
				const x = (sendFindRequest(props.coordString, props.serverSettings.serverUrl)).then((value) => {props.setFoundPlace(new Place(value[0])); props.setFoundPlace2(new Place(value[1])); props.setFoundPlace3(new Place(value[2])); props.setFoundPlace4(new Place(value[3])); props.setFoundPlace5(new Place(value[4])); });
			}
			catch (error) {
				props.setFoundPlace(undefined);
			}
		}
	}, [props.coordString]);

	return (
		<ModalBody>
			<Col>
				<Input
					onChange={(input) => props.setCoordString(input.target.value)}
					placeholder='Enter Coordinates (i.e. 0,0) or Name Of Location'
					data-testid='coord-input'
					value={props.coordString}
				/>
				<PlaceInfo foundPlace={props.foundPlace} foundPlace2={props.foundPlace2} foundPlace3={props.foundPlace3} foundPlace4={props.foundPlace4} foundPlace5={props.foundPlace5}/>
			</Col>
		</ModalBody>
	);
}

function clearPlaces(props) {
	props.setFoundPlace(undefined);
	props.setFoundPlace2(undefined);
	props.setFoundPlace3(undefined);
	props.setFoundPlace4(undefined);
	props.setFoundPlace5(undefined);
}

function PlaceInfo(props) {
	return (
		<Collapse isOpen={!!props.foundPlace }>
			<br />
			#1: 
			{props.foundPlace?.formatPlace()}
			<br />
			#2: 
			{props.foundPlace2?.formatPlace()}
			<br />
			#3: 
			{props.foundPlace3?.formatPlace()}
			<br />
			#4: 
			{props.foundPlace4?.formatPlace()}
			<br />
			#5: 
			{props.foundPlace5?.formatPlace()}
		</Collapse>
	);
}

function AddPlaceFooter(props) {
	return (
			<><Button
			color='primary'
			onClick={() => {
				if (!/[A-Za-z]/.test(props.coordString) && /[,]/.test(props.coordString)){
					props.setCoordString("");
				}
				props.append(props.foundPlace);
			} }
			data-testid = {props.testid}
			disabled={!props.foundPlace}
		>
			&nbsp;{props.buttonText}
		</Button> </>
		
	);
}

function ClearSearch(props) {
	return (
			<><Button
			color='primary'
			onClick={() => {
				props.setCoordString("");
			}
		}
			data-testid = {'clear-button'}
			disabled={!props.coordString}
			>
				Clear Search
			</Button> </>
		
	);
}

async function verifyCoordinates(coordString, setFoundPlace) {
	try {
		const latLngPlace = new Coordinates(coordString);
		const lat = latLngPlace.getLatitude();
		const lng = latLngPlace.getLongitude();
		if (isLatLngValid(lat,lng)) {
			const fullPlace = await reverseGeocode({ lat, lng });
			setFoundPlace(fullPlace);
		}
	} catch (error) {
		setFoundPlace(undefined);
	}
}

function isLatLngValid(lat,lng) {
	return (lat !== undefined && lng !== undefined);
}