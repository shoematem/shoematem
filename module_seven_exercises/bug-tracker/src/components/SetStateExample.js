import React, { Component } from "react";

class SetStateExample extends Components {
    state = { a: 1, b: 3 };

    setStateWithObject() {
        this.setState({ a: 5, c: 10 });
        console.log(this.state)
    }

    setStateWithFunction() {
        const { a, b, c } = this.state;

        this.setState(prevState => ({
            a: prevState.a * 2,
            b: prevState.b + 10,
            c: prevState.c * 10
        }), () => console.log(this.state));
    }

    render() {
        const { a, b, c } = this.state;

        return (
            <div>
                <h3>{a}</h3>
                <h3>{b}</h3>
                <h3>{c}</h3>
                
                <button onClick={this.setStateWithObject.bind(this)}>
                    Set with Object
                </button>
                
                <button onClick={this.setStateWithFunction.bind(this)}>
                    Set with Function
                </button>
            </div>
        );
    }
}

export default SetStateExample;         