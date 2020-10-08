import React from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";

export default function ProductCard({
  name,
  upVotes,
  downVotes,
  onDownVoteClick,
  onUpvoteClick,
}) {
  return (
    <Card style={{ width: "18rem" }}>
      <Card.Body>
        <Card.Title>{name}</Card.Title>

        <Card.Text>{` Upvotes ${upVotes}`}</Card.Text>
        <Card.Text>{` Downvotes ${downVotes}`}</Card.Text>
        <Button variant="primary" size="lg" block onClick={onUpvoteClick}>
          Upvotes
        </Button>
        <Button variant="secondary" size="lg" block onClick={onDownVoteClick}>
          Downvotes
        </Button>
      </Card.Body>
    </Card>
  );
}
