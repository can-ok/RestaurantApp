


class MediaService{

    getAllMedia(){
        let response=fetch("http://localhost:8080/media/getAll",{
            method:'GET'
        })

        return response;
    }


}

export default new MediaService;