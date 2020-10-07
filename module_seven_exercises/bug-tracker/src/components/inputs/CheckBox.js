import React from "react";

const CheckBox = props => {
    return (
        <div className="checkbox-toggle">
            <input
                className="form-checkbox"
                id={props.name}
                name={props.name}
                value={["on", "true", true].includes(props.value) ? "true" : "false"}
                checked={["on", "true", true].includes(props.value)}
                onChange={props.onChange}
                type="checkbox"
            />
        </div>
    );
};

export default CheckBox;