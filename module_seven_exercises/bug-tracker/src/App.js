import React, { Component } from "react";
import SubmitFormValidated from "./components/SubmitFormValidated";

class App extends Component {
    render() {
        return (
            <div className="container">
                <h3 style={{ textAlign: "center" }}>React Form - Validated</h3>
                <br />
                <SubmitFormValidated />
            </div>
        );
    }
}

export default App;