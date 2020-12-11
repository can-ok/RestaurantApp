import React from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import './Table.css'
import Select from 'react-select';
import {BiArrowBack} from 'react-icons/bi'

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






const Modal = ({open,onClose,waiters,selectWaiter}) => {

    //const waitersList=waiters.map((item)=><option key={item.id} value={item.name} >{item.name}</option>)

   /*  const waitersList=waiters.map((item)=>{
       return({label:item.name,id:item.id})
    }) */

    const waitersList=waiters.map((item)=>{
        return({label:<div><h5>{item.firstname} <img src={'data:image/png;base64,'+item.media.fileContent} width="50" /> </h5> </div> ,value:item.id}
            )
    })
   
    if(!open) return null;

    return ( 

    <>
    <div style={OVERLAY_STYLE}/>
    <div style={MODAL_STYLES}>

        <div className="Modal">
        <h3 style={{display:'block'}} >Garson Seçimi:</h3>

        <Select options={waitersList} onChange={selectWaiter} />
        <h2 className="mt-5" onClick={onClose}> <BiArrowBack/> Geri </h2>

        </div>
     
                   
    </div> 
    
    </>
    );
}
 
export default Modal;

