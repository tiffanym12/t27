import React from 'react';
import { beforeEach, describe, expect, jest, test } from '@jest/globals';
import { render, screen } from '@testing-library/react';
import OptimizeTrip from '../../../src/components/Header/OptimizeTrip'

describe('OptimizeTrip', () => {

	const props = {
		toggleOptimizeTrip: jest.fn(),
		isOpen: true,
	};

	beforeEach(() => {
		render(
			<OptimizeTrip
				isOpen={props.isOpen}
				toggleOptimizeTrip={props.toggleOptimizeTrip}
			/>
		);
	});

    test('joseph32: Add New Trip button present', async () => {
        const addButton = screen.getByTestId('add-new-trip');
        expect(addButton).toBeTruthy();
    });

	test('tmiao: Add Old Trip button present', async () => {
		const addButton = screen.getByTestId('add-old-trip');
		expect(addButton).toBeTruthy();
	})

});