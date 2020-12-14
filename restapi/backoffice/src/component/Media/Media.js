import React, { Component } from 'react';
import './media.css'

class Media extends Component {
    state = { selectedfile:null,
              imageFiles:[]   }

    onImageChange=(event)=>{
        console.log(event.target.files)
        if(event.target.files && event.target.files[0]){
            this.setState({
                selectedfile:event.target.files[0]
            })

        }
    }

    componentDidMount(){

        fetch("http://localhost:8080/media/getAll",{
            method:'GET'
        }).then((response)=>response.json())
        .then((data)=>{
            this.setState({
                imageFiles:data
            })
        })
        .catch((err)=>console.log(err))
    }

    onFileUpload=()=>{

        let {selectedfile}=this.state;
        
        console.log(selectedfile)

        if(!selectedfile){
            alert("file seçilen değil!")
            return;
        }

        const data=new FormData();
        data.append('file',selectedfile);
        data.append('imageName',selectedfile.name)

        fetch("http://localhost:8080/media/add",{
            method:'POST',
            mode:'no-cors',
            body:data
        })
        .then((response=>{
            response.text();
        })).then((result)=>{
            console.warn(result)
        }).catch((err)=>console.log(err))

    }

    render() {
        
        let imageList=this.state.imageFiles.map((image)=>
        
        <div className="col-md-3 mr-2 ml-2 mt-2 border">
        <h5 className="row justify-content-center">  {image.name}  </h5>


        <img src={'data:image/png;base64,'+image.fileContent} width="150" />
        </div>)
        

    
        return (<div className="row"> 

                <div className="col-sm">
                <input type="file" name="file" onChange={(e)=>this.onImageChange(e)} />
                <button onClick={()=>this.onFileUpload()}>Upload Image</button>

                </div>
                <div className="col-sm CardItem ">
                <div className="row">
                {imageList}

                </div>
                </div>


            </div>  );
    }
}
 
export default Media;