
import {Link} from 'react-router-dom';
import {Button,ListGroup, ListGroupItem} from 'reactstrap';

import '../../App.css'

import { GrFormAdd} from 'react-icons/gr';



const ProductList = (props) => {

    const listItem=props.items.map( (item)=>

    <div className="col-md-4 mr-2 ml-2 mt-2 border">

        
    <img src="https://cdn.shopify.com/s/files/1/0070/7032/files/camera_56f176e3-ad83-4ff8-82d8-d53d71b6e0fe.jpg?v=1527089512" width="100" height="80" />
    

    <h5 className="row justify-content-center">
    {item.title}
    </h5>

    <Link  to={`/listCategory/${item.productCategory}`} className="row justify-content-center">
    <label className="row justify-content-center">{item.productcategory.map((category)=>category.name) }</label>
    </Link>
    <label className="row justify-content-center">{item.price.toString()} tl</label>


    <label className="row justify-content-center">{item.description}</label>

    <div className="row justify-content-center">
    
       <button className="btn btn-secondary mt-2  btn-block" onClick={(e) => props.handleAddToCart(e, item)}>Ekle <GrFormAdd size={32} color="red"/> </button>

        </div>
    </div>
    );

    return ( 

        <div className="row">
            {listItem}
        </div>

     );
}
 
export default ProductList;