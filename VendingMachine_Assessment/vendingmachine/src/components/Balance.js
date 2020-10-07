import React from 'react';
import PropTypes from 'prop-types'

function Balance({ balance }) {
	return (
		<div className="result">${balance}</div>
	)
}

Balance.propTypes = {
	balance: PropTypes.number
}

export default Balance;