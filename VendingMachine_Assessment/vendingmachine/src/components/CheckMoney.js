import React, { Component } from 'react';
import PropTypes from 'prop-types';

class CheckMoney extends Component {
	render() {
		const { coins, addCoinValue, balance, id, changeBack, message, children } = this.props;
		const buttons = coins.map((vendingItem, i) => {
			return (
				<button key={i} className="coinButton" value={vendingItem} onClick={addCoinValue}>
					{`$${vendingItem.toFixed(2)}`}
				</button>
			)
		})
		
		return (
			<div className="container">
				<div className="formContainer">
					<div className="formHeaders">Total $ In:</div>
					{
						(balance === 0)
							? <div className="formInputs"></div>
							: <div className="formInputs">${balance.toFixed(2)}</div>
					}
					<div className="formButtons">{buttons}</div>
				</div>
				<br /><hr /><br />
				<div className="formContainer">
					<div className="formHeaders">Messages</div>
					<div className="formInputs2">{message}</div>
					<br />
					<span className="dummyContainer">
						<span className="formHeaders">Item:</span>
						{
							(balance === 0)
								? <span className="formInputs"></span>
								: <span className="formInputs">{id}</span>
                        }
					</span>
					<div className="formButtons">{children}</div>
				</div>
				<br /><hr /><br />
				<div className="formContainer">
					<div className="formHeaders">Change</div>
					<div className="dummyContainer">
						{
							(balance === 0)
								? <span className="formInputs2"></span>
								: <span className="formInputs2">{changeBack}</span>
                        }
					</div>
				</div>
			</div>
		)
	}
}

CheckMoney.propTypes = {
	change: PropTypes.array,
	addCoinValue: PropTypes.func,
	balance: PropTypes.number,
	changeBack: PropTypes.string,
	message: PropTypes.string,
	id: PropTypes.string,
	children: PropTypes.object
}

export default CheckMoney;