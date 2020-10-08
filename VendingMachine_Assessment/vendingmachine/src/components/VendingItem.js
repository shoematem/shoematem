import React, { Component } from 'react';
import PropTypes from 'prop-types';

class VendingItem extends Component {
	render() {
		const { purchaseItem, vendingItem, i } = this.props;
		
		return (
			<div className="singleItem" onClick={purchaseItem} id={i}>
				<span className="itemHeader" id={i}>{i + 1}</span>
				<div className="item" id={i}>{vendingItem.name}</div>
				<div className="item" id={i}>${vendingItem.price.toFixed(2)}</div>

				{vendingItem.quantity ?
					<div className="item" id={i}>Quantity Left: {vendingItem.quantity}</div> :
					<div className="item" id={i}>Out of Stock</div>
				}
			</div>
		)
	}
}

VendingItem.propTypes = {
	purchaseItem: PropTypes.func,
	vendingItem: PropTypes.object,
	i: PropTypes.number
}

export default VendingItem;