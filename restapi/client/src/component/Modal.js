import React from 'react';
import './Modal.css'
import Select from 'react-select';
import {BiArrowBack} from 'react-icons/bi'

import CashModel from './CashModal';
import CardModel from './CardModel';

const MODAL_STYLES={
    position:'fixed',
    top:'50%',
    left:'50%',
    transform:'translate(-50%,-50%)',
    zIndex:1000,
    backgroundColor:'#ffffff'
    
}

const OVERLAY_STYLE={
    position:'fixed',
    top:0,
    left:0,
    right:0,
    bottom:0,
    backgroundColor:'rgba(0,0,0,.7)',
    zIndex:1000
}


const Modal = ({open,onClose,selectPaymentType,paymentStatus,orderWithCash,orderWithCard}) => {

  

    const options = [
        { value: 'Nakit', label: 'Nakit' },
        { value: 'Kredi Kartı', label: 'Kredi Kartı' }
      ]
   
    if(!open) return null;

    return ( 

    <>
    <div style={OVERLAY_STYLE}/>
    <div style={MODAL_STYLES}>

        <div className="PaymentModel">
        <h3 style={{display:'block'}} >Ödeme Seçimi:</h3>

        <Select options={options} onChange={selectPaymentType} />
        {paymentStatus==true && <CashModel orderWithCash={orderWithCash} /> }
        {paymentStatus==false && <CardModel orderWithCard={orderWithCard}/>}
        <h2 className="mt-5" onClick={onClose}> <BiArrowBack/> Geri </h2></div>   
    </div> 
    
    </>
    );
}
 
export default Modal;