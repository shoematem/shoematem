import { store } from "react-easy-state";
function generateUniqueId() {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
        var r = (Math.random() * 16) | 0,
            v = c === "x" ? r : (r & 0x3) | 0x8;
        return v.toString(16);
    });
}

const newSubmission = {
    subject: "",
    description: "",
    type: "",
    priority: "None",
    anonymous: "false"
};

const submission_store = store({
    isEditing: false,
    selectedSubmission: Object.assign({}, newSubmission),
    submissions: {
        "a728263f-8800-44b0-840e-82deb973b424":
            { "id": "a728263f-8800-44b0-840e-82deb973b424", "subject": "Nasty Bug", "description": "Crashes the whole app. Must fix ASAP.", "type": "Bug", "priority": "High", "anonymous": "false" },
        "c5a75387-aeaf-4593-925f-edfceda30666":
            { "id": "c5a75387-aeaf-4593-925f-edfceda30666", "subject": "Cool Secret Idea", "description": "Would be cool with more llamas easter eggs.", "type": "Idea", "priority": "Medium", "anonymous": "true" },
        "6cde17fa-ee27-4abd-a160-a07acfd66675":
            { "id": "6cde17fa-ee27-4abd-a160-a07acfd66675", "subject": "Background Music is Bleh", "description": "The forced background music is terrible! Remove it!", "type": "Complaint", "priority": "High", "anonymous": "true" }
    },
    selectSubmission(id) {
        submission_store.selectedSubmission
            = submission_store.getSubmission(id);
        submission_store.isEditing = true;
        console.log("selected: " + id);
    },
    resetSubmission() {
        submission_store.selectedSubmission
            = Object.assign({}, newSubmission)
        submission_store.isEditing = false;
        console.log("submissions reset.");
    },
    createSubmission(newData) {
        newData.id = generateUniqueId();
        submission_store.submissions[newData.id]
            = Object.assign({}, newData);
        console.log("submission created: " + JSON.stringify(newData));
        return submission_store.getSubmission(newData.id);
    },
    getAllSubmissions() {
        return Object.values(submission_store.submissions);
    },
    getSubmission(id) {
        console.log("retrieving submission: " + id);
        return submission_store.submissions[id];
    },
    updateSubmission(id, newData) {
        const oldSubmission = submission_store.getSubmission(id);
        submission_store.submissions[id]
            = Object.assign({}, oldSubmission, newData);
        console.log("replacing submission: " + id);
        console.log(JSON.stringify(newData));
        return submission_store.getSubmission(id);
    },
    deleteSubmission(id) {
        console.log("removing submission: " + id);
        delete submission_store.submissions[id];
    }
});

export default submission_store;