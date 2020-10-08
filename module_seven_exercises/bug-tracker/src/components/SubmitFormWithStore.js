import React, { Component } from "react";
import { Formik } from "formik";
import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Alert from "react-bootstrap/Alert";
import TextArea from "./inputs/TextArea";
import CheckBox from "./inputs/CheckBox";
import RadioButton from "./inputs/RadioButton";
import Button from "./inputs/Button";
import Select from "./inputs/Select";
import Input from "./inputs/Input";

import { view } from "react-easy-state";
import submission_store from "../stores/submission_store"

class SubmitFormWithStore extends Component {
    state = {
        allTypes: ["Bug", "Complaint", "Idea", "Task"],
        allPriorities: ["High", "Medium", "Low", "None"]
    };

    handleValidateForm(values) {
        let errors = {};
        if (!values.subject.length > 0) {
            errors.subject = "Subject is required";
        }
        if (!values.description.length > 0) {
            errors.description = "Description is required";
        }
        if (!values.type.length > 0) {
            errors.type = "Submission type is required";
        }
        return errors;
    }

    handleClearForm(values) {
        submission_store.resetSubmission();
    }

    handleSubmitForm(values, { setSubmitting }) {
        let submission_id;
        if (values.id) {
            submission_id
                = submission_store
                    .updateSubmission(values.id, values).id;
        } else {
            submission_id
                = submission_store.createSubmission(values).id;
        }
        submission_store.resetSubmission();
        setTimeout(() => {
            const submission
                = submission_store.getSubmission(submission_id);
            setSubmitting(false);
        }, 400);
    }

    render() {
        let { allTypes, allPriorities } = this.state;
        return (
            <Container fluid>
                <Formik
                    enableReinitialize={true}
                    initialValues
                    ={submission_store.selectedSubmission}
                    validate={this.handleValidateForm}
                    onReset={this.handleClearForm}
                    onSubmit={this.handleSubmitForm}
                >
                    {formikProps => {
                        let {
                            values,
                            errors,
                            touched,
                            handleChange,
                            handleReset,
                            handleSubmit,
                            isSubmitting
                        } = formikProps;
                        return (
                            <form onSubmit={handleSubmit}>
                                <Row>
                                    <Col>
                                        <Input
                                            name={"subject"}
                                            value={values.subject}
                                            onChange={handleChange}
                                            type={"text"}
                                            title={"Subject"}
                                            placeholder={"Enter subject..."}
                                        />
                                        <Alert show={!errors.subject == ""} variant={"danger"}>
                                            {errors.subject
                                                && touched.subject
                                                && errors.subject}
                                        </Alert>
                                        <br />
                                        <TextArea
                                            name={"description"}
                                            value={values.description}
                                            onChange={handleChange}
                                            title={"Description:"}
                                            cols={30}
                                            rows={8}
                                        />
                                        <Alert show={!errors.description == ""} variant={"danger"}>
                                            {errors.description
                                                && touched.description
                                                && errors.description}
                                        </Alert>
                                        <br />
                                    </Col>
                                    <Col>
                                        <Row>
                                            <Col>
                                                <Select
                                                    name={"type"}
                                                    value={values.type}
                                                    onChange={handleChange}
                                                    title={"Type:"}
                                                    placeholder={"Select Type..."}
                                                    options={allTypes}
                                                />
                                                <Alert show={!errors.type == ""} variant={"danger"}>
                                                    {errors.type && touched.type && errors.type}
                                                </Alert>
                                            </Col>
                                            <Col>
                                                <RadioButton
                                                    name={"priority"}
                                                    value={values.priority}
                                                    onChange={handleChange}
                                                    title={"Priority:"}
                                                    options={allPriorities}
                                                />
                                            </Col>
                                        </Row>
                                        <br />
                                        <Row>
                                            <Col md={{ span: 8, offset: 2 }}>
                                                <CheckBox
                                                    name={"anonymous"}
                                                    value={values.anonymous}
                                                    onChange={handleChange}
                                                    title={"Anonymous Submission"}
                                                />
                                            </Col>
                                        </Row>
                                        <br />
                                        <br />
                                        <Row>
                                            <Col md={{ span: 6, offset: 4 }}>
                                                <Button
                                                    type="reset"
                                                    action={handleReset}
                                                    title={"Clear"}
                                                />
                                                <Button
                                                    type="submit"
                                                    disabled={isSubmitting}
                                                    title={"Submit"}
                                                />
                                            </Col>
                                        </Row>
                                    </Col>
                                </Row>
                            </form>
                        );
                    }}
                </Formik>
            </Container>
        );
    }
}

export default view(SubmitFormWithStore);