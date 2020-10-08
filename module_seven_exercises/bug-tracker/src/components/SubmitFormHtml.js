import React from "react";

const SubmitFormHtml = props => {
    return (
        <form action="#">
            <div className="form-group">
                <label htmlFor="subject">Subject:</label>
                <br />
                <input type="text" name="subject" />
            </div>

            <div className="form-group">
                <label htmlFor="description">Description:</label>
                <br />
                <textarea name="description" rows="8" cols="40" />
            </div>

            <div className="form-group">
                <label htmlFor="type">Submission Type:</label>
                <br />
                <select name="type">
                    <option value="" disabled>
                        Select type...
                    </option>

                    <option value="Bug">Bug</option>
                    <option value="Complaint">Complaint</option>
                    <option value="Idea">Idea</option>
                    <option value="Task">Task</option>
                </select>
            </div>)

            <div className="form-group">
                <label htmlFor="priority">Priority:</label>

                <br />
                <label>
                    <input type="radio" name="priority" value="High" />
                    High
                </label>

                <br />
                <label>
                    <input type="radio" name="priority" value="Medium" />
                    Medium
                </label>

                <br />
                <label>
                    <input type="radio" name="priority" value="Low" />
                    Low
                </label>

                <br />
                <label>
                    <input type="radio" name="priority" value="None" checked />
                    None
                </label>
            </div>

            <div className="form-group">
                <input type="checkbox" name="anonymous" />
                Anonymous Submission
            </div>

            <div className="btn-group">
                <button type="reset">Clear</button>
                <button type="submit">Submit</button>
            </div>
        </form>
    );
};

export default SubmitFormHTML;