import React, { Component } from "react";
import Header from "./Header";
import Contents from "./Contents";

export default class App extends Component {
  render() {
    return (
      <div>
        <Header />
        <Contents />
      </div>
    );
  }
}

//import React from 'react';
// import Header from './Header';
// import Contents from './Contents';

// function App() {
//     return (
//         <div>
//             <Header />
//             <Contents />
//         </div>
//     );
// }

// export {App};
