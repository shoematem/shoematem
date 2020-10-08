import React, { Component } from "react";

import TextArea from "./inputs/TextArea";
import CheckBox from "./inputs/CheckBox";
import RadioButton from "./inputs/RadioButton";
import Button from "./inputs/Button";
import Select from "./inputs/Select";
import Input from "./inputs/Input";

class SubmitForm extends Component {
    state = {
        submission: {
            subject: "",
            description: "",
            type: "",
            priority: "None",
            anonymous: "false"
        },

        allTypes: ["Bug", "Complaint", "Idea", "Task"],

        allPriorities: ["High", "Medium", "Low", "None"]
    };

    constructor(props) {
        super(props);
        this.handleSubmissionChange = this.handleSubmissionChange.bind(this);
        this.handleClear = this.handleClear.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmissionChange(event) {
        let name = event.target.name;
        let value = event.target.value;

        if (event.target.type === "checkbox") {
            value = event.target.value !== "true" ? "true" : "false";
        }

        this.setState(
            prevState => {
                return { submission: { ...prevState.submission, [name]: value } };
            },
            () => console.log("changed " + name + " to: " + this.state.submission[name])
        );
    }

    handleClear(event) {
        if (event) event.preventDefault();

        this.setState({
            submission: {
                subject: "",
                description: "",
                type: "",
                priority: "None",
                anonymous: "false"
            }
        });

        console.log("submission form cleared");
    }

    handleSubmit(event) {
        if (event) event.preventDefault();

        const { submission } = this.state;
        alert(JSON.stringify(submission, null, 2));
        console.log("submitted submission: " + JSON.stringify(submission));
    }

    render() {
        let { submission, allTypes, allPriorities } = this.state;

        return (
            <form>
                <Input
                    title={"Subject:"}
                    name={"subject"}
                    value={submission.subject}
                    onChange={this.handleSubmissionChange}
                    type={"text"}
                    placeholder={"Enter subject..."}
                />
                <br />

                <TextArea
                    title={"Description:"}
                    name={"description"}
                    value={submission.description}
                    onChange={this.handleSubmissionChange}
                    cols={30}
                    rows={8}
                />
                <br />

                <Select
                    title={"Type:"}
                    name={"type"}
                    value={submission.type}
                    onChange={this.handleSubmissionChange}
                    placeholder={"Select Type..."}
                    options={allTypes}
                />
                <br />

                <RadioButton
                    title={"Priority:"}
                    name={"priority"}
                    value={submission.priority}
                    onChange={this.handleSubmissionChange}
                    options={allPriorities}
                />
                <br />

                <CheckBox
                    title={"Anonymous Submission"}
                    name={"anonymous"}
                    value={submission.anonymous}
                    onChange={this.handleSubmissionChange}
                />
                <br />

                <Button type="reset" action={this.handleClear} title={"Clear"} />
                <Button type="submit" action={this.handleSubmit} title={"Submit"} />
            </form>
        );
    }
}

export default SubmitForm;