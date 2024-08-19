import React from "react";
import { Modal, ModalFooter, ModalHeader, Button, ModalBody, Col, Collapse } from "reactstrap"

export default function OptimizeTrip(props) {

    function clear() {
        props.toggleOptimizeTrip();
    }

    return (
        <Modal isOpen={props.isOpen} toggle={props.toggleOptimizeTrip}>
            <OptimizeTripHeader toggleOptimizeTrip={props.toggleOptimizeTrip}
            />
            <ModalFooter>
            <AddNewTrip
                testid = 'add-new-trip'
                buttonText = 'Add New Trip'
            />
            <AddOldTrip
                testid = 'add-old-trip'
                buttonText = 'Add Old Trip'
            />
            </ModalFooter>
        </Modal>
    );
}

function OptimizeTripHeader(props) {
    return (
        <ModalHeader className="ml-2" toggle={props.toggleOptimizeTrip}>
            Optimize Trip
        </ModalHeader>
    );
}


function AddNewTrip(props) {
    return (
		<><Button
			color='primary'
			data-testid = {props.testid}
		>
			&nbsp;{props.buttonText}
		</Button> </>
    );
}

function AddOldTrip(props) {
    return (
		<><Button
			color='primary'
			data-testid = {props.testid}
		>
			&nbsp;{props.buttonText}
		</Button> </>
    );
}