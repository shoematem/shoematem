import React, { Component } from "react";
import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

import TextArea from "./inputs/TextArea";
import CheckBox from "./inputs/CheckBox";
import RadioButton from "./inputs/RadioButton";
import Button from "./inputs/Button";
import Select from "./inputs/Select";
import Input from "./inputs/Input";

class SubmitFormBootstrap extends Component {
    state = {
        submission: {
            subject: "",
            description: "",
            type: "",
            priority: "None",
            anonymous: "false"
        },

        allTypes: ["Bug", "Complaint", "Idea", "Task"],
        allPriorities["High", "Medium", "Low", "None"]
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
                subjection: "",
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
            <Container fluid style={{ width: "35 em" }}>
                <form>
                    <Row>
                        <Col>
                            <Input
                                name={"subject"}
                                value={submission.subject}
                                onChange={this.handleSubmissionChange}
                                type={"text"}
                                title={"Subject"}
                                placeholder={"Enter subject..."}
                            />

                            <TextArea
                                name={"description"}
                                value={submission.description}
                                onChange={this.handleSubmissionChange}
                                title={"Description:"}
                                cols={30}
                                rows={8}
                            />
                        </Col>

                        <Col>
                            <Row>
                                <Col>
                                    <Select
                                        name={"type"}
                                        value={submission.type}
                                        onChange={this.handleSubmissionChange}
                                        title={"Type:"}
                                        placeholder={"Select Type..."}
                                        options={allTypes}
                                    />
                                </Col>

                                <Col>
                                    <RadioButton
                                        name={"priority"}
                                        value={submission.priority}
                                        onChange={this.handleSubmissionChange}
                                        title={"Priority:"}
                                        options={allPriorities}
                                    />
                                </Col>
                            </Row>
                            <br />

                            <Row>
                                <Col md={{ span: 10, offset: 1 }}>
                                    <CheckBox
                                        name={"anonymous"}
                                        value={submission.anonymous}
                                        onChange={this.handleSubmissionChange}
                                        title={"Anonymous Submission"}
                                    />
                                </Col>
                            </Row>
                            <br />
                            <br />

                            <Row>
                                <Col md={{ span: 8, offset: 3 }}>
                                    <Button
                                        type="reset"
                                        action={this.handleClear}
                                        title={"Clear"}
                                    />
                                    <Button
                                        type="submit"
                                        action={this.handleSubmit}
                                        title={"Submit"}
                                    />
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                </form>
            </Container>
        );
    }
}

export default SubmitFormBootstrap;