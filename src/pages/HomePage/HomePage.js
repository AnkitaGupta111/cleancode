import React from "react";
import Template from "../Template/Template";

export default function HomePage(props) {
  return (
    <div>
      <Template rule={props.rules} />
    </div>
  );
}
