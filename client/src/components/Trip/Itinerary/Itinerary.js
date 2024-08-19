import React, { useState } from 'react';
import { useToggle } from '../../../hooks/useToggle';
import { Table, Collapse } from 'reactstrap';
import { latLngToText, placeToLatLng } from '../../../utils/transformers';
import { BsChevronDown } from 'react-icons/bs';
import PlaceActions from './PlaceActions';

export default function Itinerary(props) {
	return (
		<Table responsive>
			<TripHeader
				tripName={props.tripName}
				tripDistance = "Total Trip Distance:"
				totalDistance = {props.totalDistance}
				tripInfo = "Click for More Information"
			/>
			<PlaceList
				places={props.places}
				placeActions={props.placeActions}
				selectedIndex={props.selectedIndex}
				distances= {props.distances}
			/>
		</Table>
	);
}

function TripHeader(props) {
	return (
		<thead>
			<tr>
				<th
					className='trip-header-title'
					data-testid='trip-header-title'
				>
					{props.tripName}
					<br/>
					{props.tripDistance}
					<br/>
					{props.totalDistance}
					</th>
					<th>
					{props.tripInfo}
				</th>
			</tr>
		</thead>
	);
}

function PlaceList(props) {
	return (
		<tbody>
			{props.places.map((place, index) => (
				<PlaceRow
					key={`table-${JSON.stringify(place)}-${index}`}
					place={place}
					placeActions={props.placeActions}
					selectedIndex={props.selectedIndex}
					index={index}
					distance = {props.distances[index]}
					distances = {props.distances}
				/>
			))}
		</tbody>
	);
}

function PlaceRow(props) {
	const [showFullName, toggleShowFullName] = useToggle(false);
	const name = props.place.defaultDisplayName;
	const location = latLngToText(placeToLatLng(props.place));
	return (
		<tr className={props.selectedIndex === props.index ? 'selected-row' : ''}>
			<td
				data-testid={`place-row-${props.index}`}
				onClick={() =>
					placeRowClicked(
						toggleShowFullName,
						props.placeActions.selectIndex,
						props.index
					)
				}
			>
				<strong>{name}</strong>
				<AdditionalPlaceInfo showFullName={showFullName} location={location} placeActions={props.placeActions} index={props.index} place={props.place} distance= {props.distance} distances={props.distances}/>
			</td>
			<RowArrow toggleShowFullName={toggleShowFullName} index={props.index}/>
		</tr>
	);
}

function AdditionalPlaceInfo(props) {
	return (
		<Collapse isOpen={props.showFullName}>
			{props.place.formatPlace().replace(`${props.place.defaultDisplayName}, `, '')}
			<br />
			{props.location}
			<br />
			{"Distance to next location and length of trip leg: "}
			{props.distance}
			<br />
			{"Cumulative distance traveled so far: "}
			{totalCumulative(props.distances, props.index-1)}
			<br />
			<PlaceActions placeActions={props.placeActions} index={props.index} />
		</Collapse>
	);
}

function placeRowClicked(toggleShowFullName, selectIndex, placeIndex) {
	toggleShowFullName();
	selectIndex(placeIndex);
}

function RowArrow(props) {
	return (
		<td>
			<BsChevronDown data-testid={`place-row-toggle-${props.index}`} onClick={props.toggleShowFullName}/>
		</td>
	);
}

function totalCumulative(distances, index){
	let totalDistance = 0;
	for (let i = 0; i<=index; i++){
		totalDistance += distances[i];
	}
	return totalDistance;
}
