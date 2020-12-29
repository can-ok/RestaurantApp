
import React,{useState} from 'react'
import { CSVLink } from 'react-csv'
import Button from 'react-bootstrap/Button';

export  const ExportReactCSV = ({items, fileName}) => {
    const [excel,SetExcel]=useState([])

    const exportCsv=()=>{

        var excelRows=[['id','firstName','lastName','city','address','phoneNumber']];
        let arr=items
    
        for(let i=0; i<arr.length; i++){
          console.log()
          excelRows.push([arr[i].firstName,arr[i].lastName,arr[i].city,arr[i].address,arr[i].phoneNumber])
        }
    
        SetExcel(excelRows)
    
      }

    return (
        <Button onClick={exportCsv} className="btn btn-success" >
            <CSVLink data={excel} filename={fileName}>Export</CSVLink>
        </Button>
    )
}