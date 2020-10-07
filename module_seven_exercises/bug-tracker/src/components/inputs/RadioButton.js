import React from "react";

const RadioButton = props => {
    return (
        <div>
            <label htmlFor={props.name} className="form-label">
                {props.title}
            </label>

            <div className="radio-group">
                {props.options.map((option, index) => {
                    return (
                        <React.Fragment key={index}>
                            <label>
                                <input
                                    className="form-radio"
                                    id={props.name}
                                    name={props.name}
                                    value={option}
                                    checked={props.value === option}
                                    onChange={props.onChange}
                                    type="radio"
                                />
                                {option}
                            </label>
                            <br />
                        </React.Fragment>
                    );
                })}
            </div>
        </div>
    );
};

export default RadioButton;