import React from 'react';

const PaginationBar = ({pageNumbers,paginate}) => {

    let pagination = pageNumbers.map((number) => {
        return (
          <li key={number} onClick={() => paginate(number)} className="page-item">
            <a href="#" className="page-link">
              {number}
            </a>
          </li>
        );
      });
    

    return ( 
        <div>
            <ul className="pagination d-flex justify-content-center">{pagination}</ul>
        </div>

     );
}
 
export default PaginationBar;