import React from 'react';
import { Container, Button } from 'reactstrap';
import { CLIENT_TEAM_NAME } from '../../utils/constants';
import Menu from './Menu';
import { useToggle } from '../../hooks/useToggle';
import AddPlace from './AddPlace';
import LoadFile from './LoadFile'
import ServerSettings from './ServerSettings';
import { IoMdClose } from 'react-icons/io';
import OptimizeTrip from './OptimizeTrip';

export default function Header(props) {
	const [showAddPlace, toggleAddPlace] = useToggle(false);
	const [showServerSettings, toggleServerSettings] = useToggle(false);
	const [showLoadFile, toggleLoadFile] = useToggle(false);
	const [showOptimizePlace, toggleOptimizeTrip] = useToggle(false);

	return (
		<React.Fragment>
			<HeaderContents
				toggleAbout={props.toggleAbout}
				showAbout={props.showAbout}
				placeActions={props.placeActions}
				disableRemoveAll={props.disableRemoveAll}
				toggleAddPlace={toggleAddPlace}
				toggleServerSettings={toggleServerSettings}
				toggleLoadFile={toggleLoadFile}
				toggleOptimizeTrip={toggleOptimizeTrip}
			/>
			<AppModals
				showAddPlace={showAddPlace} toggleAddPlace={toggleAddPlace}
				showLoadFile={showLoadFile} toggleLoadFile={toggleLoadFile}
				showServerSettings={showServerSettings} toggleServerSettings={toggleServerSettings}
				placeActions={props.placeActions}
				processServerConfigSuccess={props.processServerConfigSuccess}
				serverSettings={props.serverSettings}
				setTripName={props.setTripName}
				showOptimizePlace={showOptimizePlace} toggleOptimizeTrip={toggleOptimizeTrip}
			/>
		</React.Fragment>
	);
}

function HeaderContents(props) {
	return (
		<div className='full-width header vertical-center'>
			<Container>
				<div className='header-container'>
					<h1
						className='tco-text-upper header-title'
						data-testid='header-title'
					>
						{CLIENT_TEAM_NAME}
					</h1>
					<HeaderButton {...props} />
				</div>
			</Container>
		</div>
	);
}

function HeaderButton(props) {
	return props.showAbout ? (
		<Button
			data-testid='close-about-button'
			color='primary'
			onClick={() => props.toggleAbout()}
		>
			<IoMdClose size={32} />
		</Button>
	) : (
		<Menu
			toggleAbout={props.toggleAbout}
			placeActions={props.placeActions}
			toggleAddPlace={props.toggleAddPlace}
			toggleLoadFile={props.toggleLoadFile}
			disableRemoveAll={props.disableRemoveAll}
			toggleServerSettings={props.toggleServerSettings}
			toggleOptimizeTrip={props.toggleOptimizeTrip}
		/>
	);
}

function AppModals(props) {
	return (
		<>
			<AddPlace
				isOpen={props.showAddPlace}
				toggleAddPlace={props.toggleAddPlace}
				append={props.placeActions.append}
				serverSettings={props.serverSettings}
			/>
			<ServerSettings
				isOpen={props.showServerSettings}
				toggleOpen={props.toggleServerSettings}
				processServerConfigSuccess={props.processServerConfigSuccess}
				serverSettings={props.serverSettings}
			/>
			<LoadFile
				isOpen={props.showLoadFile}
				toggleLoadFile={props.toggleLoadFile}
				placeActions={props.placeActions}
				setTripName={props.setTripName}
			/>
			<OptimizeTrip
				isOpen={props.showOptimizePlace}
				toggleOptimizeTrip={props.toggleOptimizeTrip}
			/>
		</>
	);
}
