import React, { useEffect, useState, useContext } from "react";
import "./Table.css";
import AppContext from "../../AppContext";
import CustomerService from "../../api/CustomerService";
import { Link } from "react-router-dom";
import { GrFormAdd } from "react-icons/gr";

import AddCustomerModel from "./AddCustomerModal";

const MODAL_STYLES = {
  position: "fixed",
  top: "50%",
  left: "50%",
  transform: "translate(-50%,-50%)",
  zIndex: 1000,
  backgroundColor: "#ffffff",
};

const OVERLAY_STYLE = {
  position: "fixed",
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: "rgba(0,0,0,.7)",
  zIndex: 1000,
};

const CustomerModel = ({ onClose, goProduct }) => {
  const context = useContext(AppContext);
  const [pageSize, setPageSize] = useState(5);
  const [pageCount, setPageCount] = useState(1);
  const [items, setItems] = useState([]);
  const [pageNumbers, setPageNumbers] = useState([]);
  const [customerNumber, setCustomerNumber] = useState("");

  const [addCustomerModel, setCustomerModel] = useState(false);

  useEffect(() => {
    let token = context.appState.toke
      ? context.appState.token
      : localStorage.getItem("token");
    CustomerService.token = token;
    paginate(pageCount);
  }, []);

  let paginate = (number) => {
    CustomerService.getCustomers(pageSize, number-1 )
      .then((response) => {
        let arr = new Array();
        for (let i = 1; i <= Math.ceil(response.data.totalElements / pageSize);i++) {
          arr.push(i);
        }
        if (response.status == 200) {
          setItems(response.data.content);
          setPageNumbers(arr);
        }
      })
      .catch((err) => console.log(err));
  };


  let getCustomer = (number) => {
    CustomerService.findCustomer(customerNumber, pageSize, number-1)
      .then((response) => {
        if (response.status == 200)
        {
            return response.json()
        }
      })
      .then(data=>{
          console.log(data)
        let arr = new Array();
        for (let i = 1; i <= Math.ceil(data.totalElements / pageSize);i++) {
          arr.push(i);
        }
        setItems(data.content);
        setPageNumbers(arr);
      })
  };

  let selectCustomer = (customer) => {
    let appState = Object.assign({}, context.appState);
    appState.customer = customer.id;
    context.setAppState(appState);
    onClose();
    goProduct();
  };

  let customers = items.map((item) => {
    return (
      <tr key={item.id} onClick={() => selectCustomer(item)}>
        <td>{item.id}</td>
        <td>
          {item.firstName} {item.lastName}
        </td>
        <td>{item.phoneNumber} </td>
        <td></td>
      </tr>
    );
  });

  let pagination = pageNumbers.map((number) => {
    return (
      <li key={number} onClick={() =>{customerNumber===""?paginate(number):getCustomer(number) }} className="page-item">
        <a href="#" className="page-link">
          {number}
        </a>
      </li>
    );
  });

 

  return (
    <>
      <div style={OVERLAY_STYLE} />
      <div style={MODAL_STYLES}>
        <div className="row">
          <div className="col">
            <a className="btn float-left">
              <GrFormAdd size="1rem" />
              <strong>Add Customer</strong>
            </a>
          </div>
          <div className="col">
            <div className="float-right">
              <input
                name="customerNumber"
                type="text"
                onChange={(e) => setCustomerNumber(e.target.value)}
              />
              <button
                className="btn btn-primary m-3"
                onClick={() =>{ getCustomer(1)}}> Ara </button>
            </div>
          </div>
        </div>

        <div className="CustomerModel">
          <table className="table">
            <thead>
              <tr>
                <th>id</th>
                <th>İsim</th>
                <th>Telefon</th>
                <th></th>
              </tr>
            </thead>
            <tbody>{customers}</tbody>
          </table>
          <ul className="pagination d-flex justify-content-center">
            {pagination}
          </ul>
        </div>
        {addCustomerModel && <AddCustomerModel />}
      </div>
    </>
  );
};

export default CustomerModel;
