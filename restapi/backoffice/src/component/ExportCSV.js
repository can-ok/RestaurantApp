import React,{useState} from 'react'
import { CSVLink } from 'react-csv'
import Button from 'react-bootstrap/Button';

export  const ExportReactCSV = ({items, fileName}) => {
    const [excel,SetExcel]=useState([])

    const exportCsv=()=>{


        var excelRows=[["Beans"]];
        console.log(items)
        items.map(item=>{
          excelRows.push([item])
        })
        SetExcel(excelRows)
    
      }

    return (
        <Button onClick={exportCsv} className="btn btn-warning" >
            <CSVLink data={excel} filename={fileName}>Export Beans</CSVLink>
        </Button>
    )
}