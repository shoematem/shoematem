import React, { Component } from 'react';
import PropTypes from 'prop-types';

class MoneyBack extends Component {
	render() {
		const { lastPurchased, moneyBack, balance } = this.props;

		return (
			lastPurchased || balance ? <button onClick={moneyBack} className="getItemButton">
				Take your {lastPurchased ? (lastPurchased).toLowerCase() : null} {lastPurchased && balance ? 'and' : null} {balance ? ' your change.': null}
				</button> : <button className="getItemButton" disabled>Refund Money</button>
		)
	}
}

MoneyBack.propTypes = {
	balance: PropTypes.number,
	moneyBack: PropTypes.func,
	lastPurchased: PropTypes.string
}

export default MoneyBack;