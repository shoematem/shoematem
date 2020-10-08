import React, { Component } from 'react';
import PropTypes from 'prop-types';

import AllVendingItems from './components/AllVendingItems';
import CheckMoney from './components/CheckMoney';
import './components/Balance';
import MoneyBack from './components/MoneyBack';

import './App.css';

const SERVICE_URL = 'http://tsg-vending.herokuapp.com';

class App extends Component {
    constructor() {
        super();

        this.state = {
            vendingData: [],
            balance: 0,
            changeBack: "",
            coins: [1, 0.25, 0.1, 0.05],
            coinToConsole: [{
                dollars: 0,
                quarters: 0,
                dimes: 0,
                nickels: 0,
                pennies: 0,
            }],
            isPurchaseAllowed: true,
            justPurchased: "",
            message: "",
            id: ""
        }

        this.addCoinValue = this.addCoinValue.bind(this);
        this.moneyBack = this.moneyBack.bind(this);
        this.purchaseItem = this.purchaseItem.bind(this);
    }

    componentDidMount() {
        this.loadVendingData();
    }

    loadVendingData() {
        fetch(SERVICE_URL + '/items')
            .then(response => response.json())
            .then(data => {
                this.setState({ vendingData: data })
            })
    }

    addCoinValue(e) {
        const currentBalance = this.state.balance;
        const isPurchaseAllowed = this.state.isPurchaseAllowed;
        const justPurchased = this.state.justPurchased;
        const addedCoinValue = parseFloat(e.target.value, 10);
        const newBalance = currentBalance + addedCoinValue;

        if (isPurchaseAllowed) {
            this.setState({
                balance: parseFloat(newBalance.toFixed(2), 10),
                changeBack: ""
            });
        } else {
            this.setState({
                message: `Please collect your ${justPurchased.toLowerCase()}.`
            })
        }
    }

    moneyBack() {
        this.setState({
            changeBack: this.calculateChange(this.state.balance),
            balance: 0,
            isPurchaseAllowed: true,
            justPurchased: "",
            message: "",
            id: ""
        });
    }

    calculateChange(currentBalance) {
        const dollar = 1;
        const quarter = 0.25;
        const dime = 0.1;
        const nickel = 0.05;
        const penny = 0.01;

        let holdDollars = 0, holdQuarters = 0, holdDimes = 0, holdNickels = 0, holdPennies = 0;
        let returnString = '';

        while (currentBalance >= dollar) {
            currentBalance -= dollar;
            holdDollars++;
        }

        while (currentBalance >= quarter) {
            currentBalance -= quarter;
            holdQuarters++;
        }

        while (currentBalance >= dime) {
            currentBalance -= dime;
            holdDimes++;
        }

        while (currentBalance >= nickel) {
            currentBalance -= nickel;
            holdNickels++;
        }

        while (currentBalance >= penny) {
            currentBalance -= penny;
            holdPennies++;
        }

        if (holdDollars > 0) {
            returnString += 'Dollars: ' + holdDollars + '\n';
        }

        if (holdQuarters > 0) {
            returnString += 'Quarters: ' + holdQuarters + '\n';
        }

        if (holdDimes > 0) {
            returnString += 'Dimes: ' + holdDimes + '\n';
        }

        if (holdNickels > 0) {
            returnString += 'Nickels: ' + holdNickels + '\n';
        }

        if (holdPennies > 0) {
            returnString += 'Pennies: ' + holdPennies + '\n';
        }

        this.setState({
            coinToConsole: [{
                dollars: holdDollars,
                quarters: holdQuarters,
                dimes: holdDimes,
                nickels: holdNickels,
                pennies: holdPennies
            }]
        })

        return (returnString)
    }

    purchaseItem(event) {
        event.preventDefault();
        let currentBalance = this.state.balance;
        const holdBalance = currentBalance;
        const isPurchaseAllowed = this.state.isPurchaseAllowed;
        const justPurchased = this.state.justPurchased;

        const index = event.target.id;
        const currentState = this.state.vendingData;
        const itemId = currentState[index].id;
        const itemQuantity = currentState[index].quantity;
        const itemName = currentState[index].name;
        const itemPrice = currentState[index].price;
        
        if (isPurchaseAllowed) {
            if (itemQuantity > 0) {
                if (itemPrice > currentBalance) {
                    currentBalance = itemPrice - currentBalance;

                    this.setState({
                        message: 'Please deposit $' + currentBalance.toFixed(2) + '.'
                    })
                } else {
                    currentState[index].quantity -= 1;
                    currentBalance -= itemPrice;
                    
                    this.setState({
                        vendingData: currentState,
                        balance: parseFloat(currentBalance.toFixed(2), 10),
                        isPurchaseAllowed: !isPurchaseAllowed,
                        justPurchased: itemName,
                        changeBack: this.calculateChange(currentBalance),
                        message: 'Thank You!',
                        id: (parseInt(index) + 1).toString()
                    });
                }
            } else {
                this.setState({
                    message: 'SOLD OUT!'
                })
            }
        } else {
            this.setState({
                message: `Please collect your ${justPurchased.toLowerCase()}.` //
            })
        }
        
        fetch(SERVICE_URL + '/money/' + holdBalance + '/item/' + itemId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.state.vendingData),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Vending Item - Success', this.state.coinToConsole)
                this.loadVendingData();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
    
    render() {
        return (
            <div className="App">
                <div className="container">
                    <div className="vmHeader">Vending Machine App</div>
                    <hr />
                    <div className="containerLeft">
                        <AllVendingItems vendingData={this.state.vendingData} purchaseItem={this.purchaseItem} />
                    </div>
                    <div className="containerRight">
                        <CheckMoney coins={this.state.coins}
                            balance={this.state.balance}
                            addCoinValue={this.addCoinValue}
                            moneyBack={this.moneyBack}
                            changeBack={this.state.changeBack}
                            message={this.state.message}
                            id={this.state.id} >

                            <MoneyBack balance={this.state.balance}
                                moneyBack={this.moneyBack}
                                lastPurchased={this.state.justPurchased}
                                message={this.state.message} />
                        </CheckMoney>
                    </div>
                </div>    
            </div>
        );
    }
}

App.propTypes = {
    vendingData: PropTypes.array,
    purchaseItem: PropTypes.func,
    coins: PropTypes.array,
    balance: PropTypes.number,
    addCoinValue: PropTypes.func,
    moneyBack: PropTypes.func,
    lastPurchased: PropTypes.string
}

export default App;