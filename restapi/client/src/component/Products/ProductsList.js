
import {Link} from 'react-router-dom';

import '../../App.css'

import { GrFormAdd} from 'react-icons/gr';



const ProductList = (props) => {

    const listItem=props.items.map( (item)=>

    <div className="col-md-4 mr-2 ml-2 mt-2 border">

        
    <img src={'data:image/png;base64,'+item.media.fileContent} width="100" height="100" />
    

    <h5 className="row justify-content-center ">
    {item.title}
    </h5>

    <Link  to={`/listCategory/${item.productCategory}`} className="row justify-content-center">
    <label className="row justify-content-center">{item.productcategory.map((category)=>category.name) }</label>
    </Link>
    <label className="row justify-content-center">{item.price.toString()} tl</label>


    <label className="row justify-content-center">{item.description}</label>

    <div className="row align-items-end">
    
       <button className="btn btn-primary mt-2 btn-block" onClick={(e) => props.handleAddToCart(e, item)}>Ekle <GrFormAdd size={32} color="red"/> </button>

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