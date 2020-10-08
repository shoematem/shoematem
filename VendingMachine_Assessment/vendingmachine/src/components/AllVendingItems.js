import React, { Component } from 'react';
import PropTypes from 'prop-types';
import VendingItem from './VendingItem';


class AllVendingItems extends Component {
	render() {
		const { vendingData, purchaseItem } = this.props;
		const allItems = vendingData.map((vendingItem, i) => {
			return (
				<VendingItem purchaseItem={purchaseItem} vendingItem={vendingItem} key={vendingItem.id} i={i} />
			)
		})
		
		return (
			<span className="allItems">
				{allItems}
			</span>
		)
	}
}

AllVendingItems.propTypes = {
	allVendingItems: PropTypes.array,
	purchaseItem: PropTypes.func
}

export default AllVendingItems;