


class MediaService{

    getAllMedia(){
        let response=fetch("http://localhost:8080/media/getAll",{
            method:'GET'
        }).then((response)=>response.json())
        .then((data)=>{
            this.setState({
                imageFiles:data
            })
        })
        .catch((err)=>console.log(err))

        return response;
    }


}

export default new MediaService;