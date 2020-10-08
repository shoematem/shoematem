import React from "react";
import logo from "./logo.svg";
import "./App.css";
import ProductCard from "./Product";
import Container from "react-bootstrap/Container";

function App() {
  const [products, setProducts] = React.useState([
    { id: 1, name: "Jean", upVote: 1, downVote: 8 },
    { id: 2, name: "Pants", upVote: 10, downVote: 3 },
    { id: 3, name: "Socks", upVote: 5, downVote: 6 },
    { id: 4, name: "Watch", upVote: 2, downVote: 8 },
    { id: 5, name: "Belt", upVote: 14, downVote: 9 },
  ]);

  const onDownVoteClick = (e, item) => {
    if (item.downVote !== 0) {
      setProducts(
        [...products].map((product) => {
          if (product.id === item.id) {
            return {
              ...product,
              downVote: product.downVote - 1,
            };
          } else return product;
        })
      );
    }
  };

  const onUpVoteClick = (e, item) => {
    if (item.upVote !== 0) {
      setProducts(
        [...products].map((product) => {
          if (product.id === item.id) {
            return {
              ...product,
              upVote: product.upVote + 1,
            };
          } else return product;
        })
      );
    }
  };

  return (
    <div className="App">
      <Container>
        {products.map((item) => {
          return (
            <ProductCard
              key={item.id}
              id={item.id}
              name={item.name}
              upVotes={item.upVote}
              downVotes={item.downVote}
              onDownVoteClick={(e) => onDownVoteClick(e, item)}
              onUpvoteClick={(e) => onUpVoteClick(e, item)}
            />
          );
        })}
      </Container>
    </div>
  );
}

export default App;
